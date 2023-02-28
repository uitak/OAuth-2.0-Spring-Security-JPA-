package com.demo.security;

import java.util.Map;

import com.demo.domain.User;

public class OAuthAttributes {

	private String provider;
	private Map<String, Object> attributes;
	
	public OAuthAttributes(String provider, Map<String, Object> attributes) {
		this.provider = provider;
		this.attributes = attributes;
	}
	
	public User makeUser() {
		if (provider.equals("google")) {
			return ofGoogle();
		}
		else if (provider.equals("naver")) {
			return ofNaver();
		}
		else if (provider.equals("kakao")) {
			return ofKakao();
		}
		else {
			throw new IllegalArgumentException("Invalid Provider Type.");
		}
	}
	
	private User ofGoogle() {
		String username = (String)attributes.get("email");
		String fullname = (String)attributes.get("name");
		// String image = (String)attributes.get("picture");
		
		return new User(username, fullname);
	}
	
	private User ofNaver() {
		Map<String, Object> response = (Map<String, Object>)attributes.get("response");
		String username = (String)response.get("email");
		String fullname = (String)response.get("nickname");
		// String image = (String)response.get("profile_image");
		
		return new User(username, fullname);
	}
	
	private User ofKakao() {
		Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
		Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
		String username = (String)kakaoAccount.get("email");
		String fullname = (String)kakaoProfile.get("nickname");
		// String image = (String)kakaoProfile.get("thumbnail_image_url");
		
		return new User(username, fullname);
	}
}
