package com.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
@Table(name = "USER_AUTHORITY")
public class UserAuthority {
	
	@Id @GeneratedValue
	@Column(name = "USER_AUTHORITY_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@ManyToOne
	@JoinColumn(name = "AUTHORITY_ID")
	private Authority authority;
	
	public UserAuthority(User user, Authority authority) {
		this.user = user;
		this.authority = authority;
	}
	
	
	// getter 및 setter 생략.
}

