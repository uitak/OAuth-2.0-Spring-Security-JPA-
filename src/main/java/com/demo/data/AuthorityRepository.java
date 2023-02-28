package com.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	
}
