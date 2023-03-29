package com.jdc.balance.test.ledger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import com.jdc.balance.model.form.LedgerForm;

@Sql(scripts = {
		"classpath:/sql/test_users.sql",
		"classpath:/sql/test_ledgers.sql",
})
public class LedgerApiSearchTest {

	private WebTestClient client;
	
	@BeforeEach
	void setUp(WebApplicationContext context) {
		client = MockMvcWebTestClient.bindToApplicationContext(context)
				.build();
	}
	
	@ParameterizedTest
	@CsvSource({
		"Credit,1",
		"Debit,3",
		",4"
	})
	void test_search(String type, int count) {
		client.get()
			.uri(builder -> builder
					.path("/ledger")
					.queryParam("type", type)
					.build())
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(LedgerForm.class)
			.hasSize(count);
	}
	
}
