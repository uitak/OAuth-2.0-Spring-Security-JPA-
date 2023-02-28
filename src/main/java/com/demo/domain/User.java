package com.demo.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
@Table(name = "MEMBER")
public class User {
	
	@Id @GeneratedValue
	@Column(name = "USER_ID")
	private Long id;

	private String username;
	private String password;
	private String fullname;
	
	@OneToMany(mappedBy = "user",
			   fetch = FetchType.EAGER,
			   cascade = CascadeType.ALL)
	private List<UserAuthority> userAuthorities = new ArrayList<UserAuthority>();
	
	
	public User(String username, String password, String fullname) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
	}
	
	public User(String username, String fullname) {
		this.username = username;
		this.fullname = fullname;
	}
	
	
	// -- 비즈니스 로직 --	
	public void removePremium() {
		Iterator<UserAuthority> iter = this.userAuthorities.iterator();
		while(iter.hasNext()) {
			if (iter.next().getAuthority().getName() == AuthorityType.PREMIUM) {
				iter.remove();
			}
		}
	}
	
	// -- 연관관계 편의 메서드 --
	public void addUserAuthority(UserAuthority userAuthority) {
		userAuthorities.add(userAuthority);
		userAuthority.setUser(this);
	}

	// getter와 setter 생략.
}
