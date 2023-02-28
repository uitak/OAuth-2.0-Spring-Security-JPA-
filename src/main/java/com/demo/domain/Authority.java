package com.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PROTECTED, force=true)
@Entity
@Table(name = "AUTHORITY")
public class Authority {
	
	@Id @GeneratedValue
	@Column(name = "AUTHORITY_ID")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private AuthorityType name;
	
	public Authority(AuthorityType type) {
		this.name = type;
	}
}
