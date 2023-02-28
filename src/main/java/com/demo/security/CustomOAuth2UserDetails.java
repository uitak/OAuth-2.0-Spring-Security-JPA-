package com.demo.security;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.demo.domain.User;
import com.demo.domain.UserAuthority;

public class CustomOAuth2UserDetails implements OAuth2User {

	private final User user;
	private final Map<String, Object> attributes;
	private final String userNameAttributeName;

	public CustomOAuth2UserDetails(Map<String, Object> attributes, User user, String userNameAttributeName) {
		this.user = user;
		this.attributes = attributes;
		this.userNameAttributeName = userNameAttributeName;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	@Override
	public String getName() {
		/*
		String sub = attributes.get("sub").toString();
		return sub;
		*/
		return userNameAttributeName;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("CustomOAuth2UserDetails 클래스의 getAuthorities() 메서드 실행");
		
		return user.getUserAuthorities().stream()
				.map(a -> new SimpleGrantedAuthority(a.getAuthority().getName().toString()))
				.collect(Collectors.toList());
	}
	
}
