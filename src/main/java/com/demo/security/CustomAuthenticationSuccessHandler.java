package com.demo.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication) throws IOException {
		
		var authorities = authentication.getAuthorities();
		var auth = authorities.stream()
							  .filter(a -> (a.getAuthority().equals("ADMIN") ||
									  	    a.getAuthority().equals("DBA")))
							  .findFirst();
		
		if (auth.isPresent()) {
			response.sendRedirect("/admin");
		}
		else {
			response.sendRedirect("/");
		}
	}
}
