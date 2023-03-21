package com.jdc.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.jdc.balance.security.AppTokenFilter;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:/token.properties")
@ComponentScan(basePackages = "com.jdc.balance.security")
public class BalanceAppSecurityConfig {
	
	@Autowired
	private AppTokenFilter appTokenFilter;
	
	@Bean
	HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
		return new HandlerMappingIntrospector();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.cors(cors -> cors.and());
		
		http.csrf(csrf -> csrf.disable());
		
		http.authorizeHttpRequests(authority -> 
				authority
					.requestMatchers("/security/**").permitAll()
					.requestMatchers("/account/**").hasAuthority("Admin")
					.anyRequest().authenticated());
		
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(appTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
