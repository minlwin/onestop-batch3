package com.jdc.balance.test.ledger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

@Sql(scripts = {
		"classpath:/sql/test_users.sql",
		"classpath:/sql/test_ledgers.sql",
})
public class LedgerApiUploadTest {

	private WebTestClient client;
	
	@BeforeEach
	void setUp(WebApplicationContext context) {
		client = MockMvcWebTestClient.bindToApplicationContext(context)
				.build();
	}

	@Test
	@Order(1)
	void test_upload_validation_error() {
		var builder = new MultipartBodyBuilder();
		builder.part("file", new ClassPathResource("/files/ledger_upload_error.txt"));
		
	}
	
	@Test
	@Order(2)
	void test_upload_success() {
		
		var builder = new MultipartBodyBuilder();
		builder.part("file", new ClassPathResource("/files/ledger_upload.txt"));
		
		
	}
}
