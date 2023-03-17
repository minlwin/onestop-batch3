package com.jdc.balance.test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import com.jdc.balance.BalanceAppWebConfig;
import com.jdc.balance.model.dto.LedgerType;
import com.jdc.balance.model.dto.MessageDto;
import com.jdc.balance.model.dto.MessageDto.Type;
import com.jdc.balance.model.form.LedgerForm;

@WithMockUser(username = "test", authorities = "Member")
@TestMethodOrder(value = OrderAnnotation.class)
@SpringJUnitWebConfig(classes = BalanceAppWebConfig.class)
@Sql(statements = {
		"set foreign_key_checks = 0",
		"truncate table ACCOUNT",
		"insert into ACCOUNT values (1, 'test@gmail.com', 'test', 'Test User', '09 1111 2222', '2023-03-15', 1, 1)",
		"set foreign_key_checks = 1"
})
public class LedgerApiTest {

	private WebTestClient client;
	
	@BeforeEach
	void setUp(WebApplicationContext context) {
		client = MockMvcWebTestClient.bindToApplicationContext(context)
				.build();
	}
	
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(delimiter = '\t', resources = "/ledger_type_create_error.txt")
	void test_create_no_name_error(LedgerType type, String name, String messages) {
		var result = client.post()
			.uri("/ledger")
			.bodyValue(new LedgerForm(0, type, name))
			.exchange()
			.expectStatus().isEqualTo(HttpStatusCode.valueOf(406))
			.expectBody(MessageDto.class)
			.returnResult()
			.getResponseBody();
		
		assertNotNull(result);
		assertEquals(Type.Validation, result.type());
		
		var expectedMessages = messages.split(",");
		assertEquals(expectedMessages.length, result.messages().size());
		
		assertThat(result.messages(), allOf(
				notNullValue(),
				containsInAnyOrder(expectedMessages)
		));
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvSource({
		"Credit,Demo Name",
		"Debit,Debit Name"
	})
	void test_create_success(LedgerType type, String name) {
		var result = client.post()
				.uri("/ledger")
				.bodyValue(new LedgerForm(0, type, name))
				.exchange()
				.expectStatus().isCreated()
				.expectBody(LedgerForm.class)
				.returnResult()
				.getResponseBody();
		
		
		assertThat(result, is(new LedgerForm(1, type, name)));
	}
}
