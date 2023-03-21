package com.jdc.balance.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AppTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private AppTokenProvider tokenProvider;
	
	@Value("${app.token.name}")
	private String tokenName;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Login With Token
		tokenProvider.authenticate(request.getHeader(tokenName));

		filterChain.doFilter(request, response);
	}

}
