package com.jdc.balance.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.jdc.balance.BalanceAppWebConfig;

@TestMethodOrder(value = OrderAnnotation.class)
@SpringJUnitWebConfig(classes = BalanceAppWebConfig.class)
public class AccountApiTest {

	@Order(1)
	void test_search() {
		
	}
	
	@Order(2)
	void test_find_id_not_found() {
		
	}
	
	@Order(3)
	void test_find_by_id_success() {
		
	}
	
	@Order(4)
	void test_update_status_not_found() {
		
	}
	
	@Order(5)
	void test_update_status_validation_error() {
		
	}

	@Order(6)
	void test_update_status_success() {
		
	}
	
}
