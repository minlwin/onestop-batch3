package com.jdc.balance.test.balance;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import com.jdc.balance.BalanceAppJpaConfig;
import com.jdc.balance.BalanceAppSecurityConfig;
import com.jdc.balance.BalanceAppWebConfig;

@WithMockUser(username = "test", authorities = "Member")
@SpringJUnitWebConfig(classes = {
		BalanceAppJpaConfig.class,
		BalanceAppSecurityConfig.class,
		BalanceAppWebConfig.class
})
@Sql(scripts = {
		"classpath:/sql/test_users.sql",
		"classpath:/sql/test_ledgers.sql",
})
public class BalanceApiUpdateTest {

	WebTestClient client;
	
	@BeforeEach
	void setUp(WebApplicationContext context) {
		client = MockMvcWebTestClient.bindToApplicationContext(context)
				.build();
	}

	void test_update_no_data() {
		
	}

	void test_update_validation_error() {
		
	}
	
	void test_update_success() {
		
	}

}
