package com.jdc.balance.security;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AppTokenProvider {
	
	private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	@Value("${app.token.limit}")
	private int limit;
	@Value("${app.token.issuer}")
	private String issuer;
	
	@Value("${app.token.role.name}")
	private String roleName;
	
	public void authenticate(String tokenValue) {
		
		try {
			if(StringUtils.hasLength(tokenValue)) {
				var jwt = Jwts.parserBuilder()
					.requireIssuer(issuer)
					.setSigningKey(key)
					.build()
					.parseClaimsJws(tokenValue);
				
				var loginId = jwt.getBody().getSubject();
				var roles = jwt.getBody().toString();
				
				var auth = new UsernamePasswordAuthenticationToken(loginId, null, 
						AuthorityUtils.createAuthorityList(roles.split(",")));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public String generateToken(Authentication authentication) {
		
		if(authentication instanceof UsernamePasswordAuthenticationToken auth) {
			
			var issueAt = new Date();
			var expiredAt = Calendar.getInstance();
			expiredAt.setTime(issueAt);
			expiredAt.add(Calendar.MINUTE, limit);
			
			return Jwts.builder()
					.setIssuedAt(issueAt)
					.setExpiration(expiredAt.getTime())
					.setIssuer(issuer)
					.setSubject(auth.getName())
					.claim(roleName, auth.getAuthorities()
							.stream().map(a -> a.getAuthority())
							.collect(Collectors.joining(",")))
					.signWith(key)
					.compact();
		}
		
		return null;
	}

}
