package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class SecurityConfig {
		
	@Autowired
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private CustomAuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	
	//private CustomOAuth2UserService customOAuth2UserService = new CustomOAuth2UserService();
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.formLogin()
			.loginPage("/login")
    		.successHandler(authenticationSuccessHandler)
    		.failureHandler(authenticationFailureHandler)
    		.and()
    		.logout()
    		.logoutSuccessUrl("/");
		
		
		http.oauth2Login()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.userInfoEndpoint()
			.userService(customOAuth2UserService);
		
		
        http.authorizeHttpRequests(authorize -> authorize
        	.requestMatchers("/**").permitAll()
        	.requestMatchers("/admin/**").hasRole("ADMIN")
        	.requestMatchers("/db/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') and hasRole('DBA')"))   
    		.anyRequest().denyAll()
    	);
        
        // /h2-console 페이지 표시 안될 경우 필요.
        http.headers().frameOptions().disable();
        http.csrf().disable();

        return http.build();
    }
}
