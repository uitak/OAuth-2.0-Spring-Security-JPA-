package com.demo.security;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.data.UserRepository;
import com.demo.domain.User;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) {
		System.out.println("JpaUserService의 loadUserByUsername() 실행 시작 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		Supplier<UsernameNotFoundException> s =
				() -> new UsernameNotFoundException(
						"Problem during authentiation!");
		
		User u = userRepo
				.findUserByUsername(username)
				.orElseThrow(s);
		return new CustomUserDetails(u);
	}
}
