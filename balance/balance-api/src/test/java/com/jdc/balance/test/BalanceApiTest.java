package com.jdc.balance.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.jdc.balance.BalanceAppWebConfig;

@TestMethodOrder(value = OrderAnnotation.class)
@SpringJUnitWebConfig(classes = BalanceAppWebConfig.class)
public class BalanceApiTest {
	
	@Order(1)
	void test_search() {
		
	}

	@Order(2)
	void test_find_by_id_not_found() {
		
	}

	@Order(3)
	void test_find_by_id_success() {
		
	}
	
	@Order(4)
	void test_create_validation_error() {
		
	}
	
	@Order(5)
	void test_create_success() {
		
	}
	
	@Order(6)
	void test_update_no_data() {
		
	}

	@Order(7)
	void test_update_validation_error() {
		
	}
	
	@Order(8)
	void test_update_success() {
		
	}
}
