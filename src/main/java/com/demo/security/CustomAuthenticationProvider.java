package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication)
		throws AuthenticationException {
		System.out.println("AuthenticationProvider의 authenticate() 실행 시작 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		CustomUserDetails user = userDetailsService.loadUserByUsername(username);
		
		if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(
					user.getUsername(),
					user.getPassword(),
					user.getAuthorities());
		}
		else {
			throw new BadCredentialsException("Something went wrong!");
		}
	}
	
	@Override
	public boolean supports(Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}
}
