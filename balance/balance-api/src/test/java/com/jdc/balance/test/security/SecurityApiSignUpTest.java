package com.jdc.balance.test.security;

import org.junit.jupiter.api.Order;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.jdc.balance.BalanceAppJpaConfig;
import com.jdc.balance.BalanceAppSecurityConfig;
import com.jdc.balance.BalanceAppWebConfig;

@SpringJUnitWebConfig(classes = {
		BalanceAppJpaConfig.class,
		BalanceAppSecurityConfig.class,
		BalanceAppWebConfig.class
})
public class SecurityApiSignUpTest {

	@Order(1)
	void test_sign_up_validation_error() {
		
	}
	
	@Order(2)
	void test_sign_up_success() {
		
	}

}
