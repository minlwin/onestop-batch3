package com.jdc.balance.test.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import com.jdc.balance.model.dto.AccountDto;

@Sql(scripts = {
		"classpath:/sql/test_users.sql",
})
public class AccountApiSearchTest {

	private WebTestClient client;
	
	@BeforeEach
	void setUp(WebApplicationContext context) {
		client = MockMvcWebTestClient.bindToApplicationContext(context)
				.build();
	}

	@ParameterizedTest
	@CsvSource({
		"Approved,,4",
		"Approved,oth,1",
		"Approved,test,2",
		",,6",
		"Apply,oth,0"
	})
	void test_search(String status, String name, int count) {
		client.get()
			.uri(builder -> builder.path("/account")
					.queryParam("status", status)
					.queryParam("name", name)
					.build()).exchange()
			.expectStatus().isOk()
			.expectBodyList(AccountDto.class).hasSize(count);
	}

}
