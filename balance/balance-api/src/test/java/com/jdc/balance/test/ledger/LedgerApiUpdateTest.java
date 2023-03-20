package com.jdc.balance.test.ledger;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
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
@Sql(scripts = {
		"classpath:/sql/test_users.sql",
		"classpath:/sql/test_ledgers.sql",
})
public class LedgerApiUpdateTest {

	private WebTestClient client;
	
	@BeforeEach
	void setUp(WebApplicationContext context) {
		client = MockMvcWebTestClient.bindToApplicationContext(context)
				.build();
	}
	
	@Order(1)
	@ParameterizedTest
	@CsvSource({
		"0,Credit,Demo Name",
		"7,Debit,Debit Name"
	})
	void test_update_no_data_error(int id, LedgerType type, String name) {
		var result = client.put()
				.uri("/ledger")
				.bodyValue(new LedgerForm(id, type, name))
				.exchange()
				.expectStatus().isNoContent()
				.expectBody(MessageDto.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(result).matches(
			a -> a.type() == Type.Business 
				&& a.messages().size() == 1 
				&& a.messages().get(0).equals("There is no ledger with id %d.".formatted(id))
				);
	}

	@Order(2)
	@ParameterizedTest
	@CsvSource({
		"1,Credit,",
		"2,,Test name"
	})
	void test_update_validation_error(int id, LedgerType type, String name) {
		var result = client.put()
			.uri("/ledger")
			.bodyValue(new LedgerForm(id, type, name))
			.exchange()
			.expectStatus().isEqualTo(HttpStatusCode.valueOf(406))
			.expectBody(MessageDto.class)
			.returnResult()
			.getResponseBody();
		
		assertThat(result).matches(a -> a.type() == Type.Validation);
	}
	
	
	@Order(3)
	@ParameterizedTest
	@CsvSource({
		"1,Debit,Test Name",
		"2,Credit,Test Name"
	})
	void test_update_success(int id, LedgerType type, String name) {
		
		var result = client.put()
				.uri("/ledger")
				.bodyValue(new LedgerForm(id, type, name))
				.exchange()
				.expectStatus().isAccepted()
				.expectBody(LedgerForm.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(result).isEqualTo(new LedgerForm(id, type, name));
	}	

}
