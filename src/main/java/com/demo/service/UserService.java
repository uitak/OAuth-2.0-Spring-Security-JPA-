package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.data.AuthorityRepository;
import com.demo.data.UserRepository;
import com.demo.domain.Authority;
import com.demo.domain.AuthorityType;
import com.demo.domain.RegistrationForm;
import com.demo.domain.User;
import com.demo.domain.UserAuthority;

import lombok.NoArgsConstructor;

@Service
@Transactional
@NoArgsConstructor
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AuthorityRepository authRepo;
	
	//@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public Long createUser(RegistrationForm form) {
		
		if (userRepo.findUserByUsername(form.getUsername()) == null) {
			System.out.println("해당 id는 이미 존재합니다.");
			return (long) -1;
		}
		
		User user = new User(form.getUsername(),
							 bCryptPasswordEncoder.encode(form.getPassword()),
							 form.getFullname());
		
		Authority basicAuth = new Authority(AuthorityType.ORDINARY);		
		authRepo.save(basicAuth);

		UserAuthority userAuth = new UserAuthority(user, basicAuth);
		user.addUserAuthority(userAuth);		
		userRepo.save(user);
		
		return user.getId();
	}
	
	
	public Long createOAuthUser(User user) {
		
		System.out.println("createOAuthUser 실행 ######################");
		
		if (userRepo.findUserByUsername(user.getUsername()) == null) {
			System.out.println("해당 id는 이미 존재합니다.");
			return (long) -1;
		}
		
		Authority basicAuth = new Authority(AuthorityType.ORDINARY);		
		authRepo.save(basicAuth);
		
		UserAuthority userAuth = new UserAuthority(user, basicAuth);
		user.addUserAuthority(userAuth);		
		userRepo.save(user);		
				
		return user.getId();
	}
	
}
