package com.jdc.balance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class AppTokenResponseAdvice implements ResponseBodyAdvice<Object>{

	@Autowired
	private AppTokenProvider tokenProvider;
	@Value("${app.token.name}")
	private String tokenName;
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		
		// Check Login
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication.isAuthenticated()) {
			var webToken = tokenProvider.generateToken(authentication);
			
			if(StringUtils.hasLength(webToken)) {
				
				if(response instanceof ServletServerHttpRequest httpResponse) {
					httpResponse.getHeaders().add(tokenName, webToken);
				}
			}
		}
		
		return body;
	}

}
