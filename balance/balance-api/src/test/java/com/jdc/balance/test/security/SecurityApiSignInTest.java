package com.jdc.balance.test.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import com.jdc.balance.BalanceAppJpaConfig;
import com.jdc.balance.BalanceAppSecurityConfig;
import com.jdc.balance.BalanceAppWebConfig;
import com.jdc.balance.model.dto.LoginUserDto;
import com.jdc.balance.model.dto.MessageDto;
import com.jdc.balance.model.dto.Role;
import com.jdc.balance.model.dto.MessageDto.Type;
import com.jdc.balance.model.form.SignInForm;

@TestMethodOrder(value = OrderAnnotation.class)
@SpringJUnitWebConfig(classes = { BalanceAppJpaConfig.class, BalanceAppSecurityConfig.class,
		BalanceAppWebConfig.class })
public class SecurityApiSignInTest {

	WebTestClient client;
	@Value("${app.token.name}")
	private String tokenName;

	@BeforeEach
	void setUp(WebApplicationContext context) {
		client = MockMvcWebTestClient.bindToApplicationContext(context).build();
	}

	@Order(1)
	@ParameterizedTest
	@CsvSource(value = { ":password:Please enter login id.", "admin::Please enter password.",
			"::Please enter login id.,Please enter password." }, delimiter = ':')
	void test_sign_in_validation_error(String loginId, String password, String messages) {
		var result = client.post().uri(builder -> builder.path("/security/sign-in").build())
				.bodyValue(new SignInForm(loginId, password)).exchange().expectStatus()
				.isEqualTo(HttpStatusCode.valueOf(406)).expectBody(MessageDto.class).returnResult().getResponseBody();

		assertThat(result).extracting(MessageDto::type).isEqualTo(Type.Validation);
		assertThat(result).extracting(MessageDto::messages).asList()
				.allMatch(data -> Arrays.asList(messages.split(",")).contains(data));
	}

	@Order(2)
	@ParameterizedTest
	@CsvSource({ 
		"admins,admin,Please check your login id.",
		"admin,Admin,Please check your password."
		})
	void test_sign_in_auth_failure(String loginId, String password, String messages) {
		
		var result = client.post().uri(builder -> builder.path("/security/sign-in").build())
				.bodyValue(new SignInForm(loginId, password)).exchange()
				.expectStatus().isForbidden()
				.expectBody(MessageDto.class)
				.returnResult().getResponseBody();
		
		assertThat(result).extracting(MessageDto::type).isEqualTo(Type.Platform);
		assertThat(result).extracting(MessageDto::messages).asList()
				.allMatch(data -> Arrays.asList(messages.split(",")).contains(data));
	}

	@Order(3)
	@Test
	void test_sign_in_success() {
		
		var result = client.post().uri(builder -> builder.path("/security/sign-in").build())
				.bodyValue(new SignInForm("admin", "admin")).exchange()
				.expectStatus().isOk()
				.expectHeader().exists(tokenName)
				.expectBody(LoginUserDto.class)
				.returnResult().getResponseBody();
		
		assertThat(result).extracting(LoginUserDto::id).isEqualTo(1);
		assertThat(result).extracting(LoginUserDto::role).isEqualTo(Role.Admin);
	}

}
