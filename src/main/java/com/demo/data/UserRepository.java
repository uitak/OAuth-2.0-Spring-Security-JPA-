package com.demo.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByUsername(String username);
}
