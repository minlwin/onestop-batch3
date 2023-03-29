package com.jdc.balance.test.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import com.jdc.balance.model.dto.LoginUserDto;
import com.jdc.balance.model.dto.MessageDto;
import com.jdc.balance.model.dto.MessageDto.Type;
import com.jdc.balance.model.dto.Role;
import com.jdc.balance.model.form.SignUpForm;

@SpringBootTest
@ActiveProfiles("local")
@TestMethodOrder(value = OrderAnnotation.class)
public class SecurityApiSignUpTest {

	WebTestClient client;
	@Value("${app.token.name}")
	private String tokenName;

	@BeforeEach
	void setUp(WebApplicationContext context) {
		client = MockMvcWebTestClient.bindToApplicationContext(context).build();
	}

	@Order(1)
	@ParameterizedTest
	@CsvSource(value = { 
			":member:password:Please enter user name.", 
			"name::password:Please enter login id.", 
			"name:member::Please enter password.",
			":::Please enter user name.,Please enter login id.,Please enter password." }, delimiter = ':')
	void test_sign_up_validation_error(String name, String loginId, String password, String messages) {
		var result = client.post().uri(builder -> builder.path("/security/sign-up").build())
				.bodyValue(new SignUpForm(name, loginId, password)).exchange().expectStatus()
				.isEqualTo(HttpStatusCode.valueOf(406)).expectBody(MessageDto.class).returnResult().getResponseBody();

		assertThat(result).extracting(MessageDto::type).isEqualTo(Type.Validation);
		assertThat(result).extracting(MessageDto::messages).asList()
				.allMatch(data -> Arrays.asList(messages.split(",")).contains(data));
		
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvSource("Member,member,member")
	void test_sign_up_success(String name, String loginId, String password) {
		var result = client.post().uri(builder -> builder.path("/security/sign-up").build())
				.bodyValue(new SignUpForm(name, loginId, password)).exchange()
				.expectStatus().isOk()
				.expectHeader().exists(tokenName)
				.expectBody(LoginUserDto.class)
				.returnResult().getResponseBody();
		
		assertThat(result).extracting(LoginUserDto::id).isEqualTo(2);
		assertThat(result).extracting(LoginUserDto::name).isEqualTo(name);
		assertThat(result).extracting(LoginUserDto::loginId).isEqualTo(loginId);
		assertThat(result).extracting(LoginUserDto::role).isEqualTo(Role.Member);
		
	}

}
