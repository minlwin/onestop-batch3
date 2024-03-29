package com.jdc.balance.test.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.dto.AccountStatus;
import com.jdc.balance.model.dto.MessageDto;
import com.jdc.balance.model.dto.MessageDto.Type;

@SpringBootTest
@ActiveProfiles("local")
@Sql(scripts = {
		"classpath:/sql/test_users.sql",
})
public class AccountApiFindByIdTest {

	private WebTestClient client;
	
	@BeforeEach
	void setUp(WebApplicationContext context) {
		client = MockMvcWebTestClient.bindToApplicationContext(context)
				.build();
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 7})
	void test_find_id_not_found(int id) {
		
		var result = client.get().uri(builder -> builder.path("/account/{id}").build(id))
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody(MessageDto.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(result).matches(a -> 
			a.type() == Type.Business &&
			a.messages().size() == 1);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/params/account_find_by_id.txt")
	void test_find_by_id_success(
			int id,
			String email,
			String loginId,
			String name,
			String phone,
			LocalDate registDate,
			AccountStatus status) {
	
		var result = client.get().uri(builder -> builder.path("/account/{id}").build(id))
				.exchange()
				.expectStatus().isOk()
				.expectBody(AccountDto.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(result).extracting(a -> a.id()).isEqualTo(id);
		assertThat(result).extracting(a -> a.name()).isEqualTo(name);
		assertThat(result).extracting(a -> a.loginId()).isEqualTo(loginId);
		assertThat(result).extracting(a -> a.phone()).isEqualTo(phone);
		assertThat(result).extracting(a -> a.email()).isEqualTo(email);
		assertThat(result).extracting(a -> a.status()).isEqualTo(status);
		assertThat(result).extracting(a -> a.registDate()).isEqualTo(registDate);
		
	}

}
