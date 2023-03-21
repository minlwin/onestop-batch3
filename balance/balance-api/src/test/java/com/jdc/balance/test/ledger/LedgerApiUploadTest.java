package com.jdc.balance.test.ledger;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.BodyInserters;

import com.jdc.balance.BalanceAppJpaConfig;
import com.jdc.balance.BalanceAppSecurityConfig;
import com.jdc.balance.BalanceAppWebConfig;
import com.jdc.balance.model.dto.MessageDto;
import com.jdc.balance.model.dto.MessageDto.Type;
import com.jdc.balance.model.dto.UploadResultDto;

@WithMockUser(username = "test", authorities = "Member")
@TestMethodOrder(value = OrderAnnotation.class)
@SpringJUnitWebConfig(classes = {
		BalanceAppJpaConfig.class,
		BalanceAppSecurityConfig.class,
		BalanceAppWebConfig.class
})
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
		
		var result = client.post().uri("/ledger/upload")
			.body(BodyInserters.fromMultipartData(builder.build()))
			.exchange()
			.expectStatus().isEqualTo(HttpStatusCode.valueOf(406))
			.expectBody(MessageDto.class)
			.returnResult().getResponseBody();
		
		assertThat(result).matches(a -> 
			a.type() == Type.Validation && 
			a.messages().size() == 3);
	}
	
	@Test
	@Order(2)
	void test_upload_success() {
		
		var builder = new MultipartBodyBuilder();
		builder.part("file", new ClassPathResource("/files/ledger_upload.txt"));
		
		var result = client.post().uri("/ledger/upload")
			.body(BodyInserters.fromMultipartData(builder.build()))
			.exchange()
			.expectBody(UploadResultDto.class)
			.returnResult().getResponseBody();
		
		assertThat(result).matches(a -> a.success() && a.size() == 3 && a.message().equals("Successfully Uploaded."));
		
	}
}
