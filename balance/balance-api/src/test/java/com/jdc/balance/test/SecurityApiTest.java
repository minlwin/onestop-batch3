package com.jdc.balance.test;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.jdc.balance.BalanceAppWebConfig;

@TestMethodOrder(value = OrderAnnotation.class)
@SpringJUnitWebConfig(classes = BalanceAppWebConfig.class)
public class SecurityApiTest {

	@Order(1)
	void test_sign_up_validation_error() {
		
	}
	
	@Order(2)
	void test_sign_up_success() {
		
	}
	
	@Order(3)
	void test_sign_in_validation_error() {
		
	}
	
	@Order(4)
	void test_sign_in_success() {
		
	}
}
