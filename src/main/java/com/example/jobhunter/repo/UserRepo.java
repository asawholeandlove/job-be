package com.example.jobhunter.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobhunter.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
  User findByEmail(String email);
}
