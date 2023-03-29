package com.jdc.balance.test.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.dto.AccountStatus;
import com.jdc.balance.model.dto.MessageDto;
import com.jdc.balance.model.dto.MessageDto.Type;
import com.jdc.balance.model.form.AccountStatusForm;

@Sql(scripts = {
		"classpath:/sql/test_users.sql",
})
public class AccountApiUpdateStatusTest {

	private WebTestClient client;
	
	@BeforeEach
	void setUp(WebApplicationContext context) {
		client = MockMvcWebTestClient.bindToApplicationContext(context)
				.build();
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 7})
	void test_update_status_not_found(int id) {
		var result = client.put().uri("/account/status")
				.bodyValue(new AccountStatusForm(id, AccountStatus.Approved)).exchange()
				.expectStatus().isNoContent()
				.expectBody(MessageDto.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(result).extracting(MessageDto::type).isEqualTo(Type.Business);
		assertThat(result).extracting(MessageDto::messages).asList().hasSize(1);
		
	}
	
	@ParameterizedTest
	@CsvSource({
		"3,,1",
		",Approved,1",
		",,2"
	})
	void test_update_status_validation_error(Integer id, AccountStatus status, int messages) {
		
		var result = client.put().uri("/account/status")
				.bodyValue(new AccountStatusForm(id, status)).exchange()
				.expectStatus().isEqualTo(HttpStatusCode.valueOf(406))
				.expectBody(MessageDto.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(result).extracting(MessageDto::type).isEqualTo(Type.Validation);
		assertThat(result).extracting(MessageDto::messages).asList().hasSize(messages);
		
	}

	@ParameterizedTest
	@CsvSource({
		"4,Approved",
		"5,Denied",
		"6,Approved"
	})
	void test_update_status_success(int id, AccountStatus status) {
		var result = client.put().uri("/account/status")
				.bodyValue(new AccountStatusForm(id, status)).exchange()
				.expectStatus().isAccepted()
				.expectBody(AccountDto.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(result).extracting(AccountDto::id).isEqualTo(id);
		assertThat(result).extracting(AccountDto::status).isEqualTo(status);
	}
}
