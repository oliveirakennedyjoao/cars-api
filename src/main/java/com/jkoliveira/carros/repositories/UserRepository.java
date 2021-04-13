package com.jkoliveira.carros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jkoliveira.carros.models.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	
}
