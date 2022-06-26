package io.kadev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kadev.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
