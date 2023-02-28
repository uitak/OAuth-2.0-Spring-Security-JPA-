package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.demo.data.UserRepository;
import com.demo.domain.User;
import com.demo.service.UserService;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userSvc;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) 
			throws OAuth2AuthenticationException {
		
		System.out.println("OAuth2Service의 loadUser() 실행 시작 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		String provider = userRequest.getClientRegistration().getRegistrationId();
		// OAuth 로그인 시 키(key)가 되는 값.
		String userNameAttributeName = userRequest.getClientRegistration()
                								  .getProviderDetails()
                								  .getUserInfoEndpoint()
                								  .getUserNameAttributeName();
        /*
        System.out.println("#############################################");
        oauth2User.getAttributes().forEach((k,v)->{
            System.out.println(k + ":" + v);
        });
        System.out.println("#############################################");
        */
        
        OAuthAttributes oauthAttributes = new OAuthAttributes(provider, oauth2User.getAttributes());
        User user = oauthAttributes.makeUser();
        
        System.out.println("userSvc 객체의 createOAuthUser 호출 ################");
        Long userId = userSvc.createOAuthUser(user);
        if (userId >= 0) {
        	user = userRepo.findById(userId).get();
        }
        else {
        	user = userRepo.findUserByUsername(user.getUsername()).get();
        }
        
        return new CustomOAuth2UserDetails(oauth2User.getAttributes(), user, userNameAttributeName);
	}
}
