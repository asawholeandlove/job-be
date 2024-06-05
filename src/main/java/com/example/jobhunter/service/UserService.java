package com.example.jobhunter.service;

import org.springframework.stereotype.Service;

import com.example.jobhunter.model.User;
import com.example.jobhunter.repo.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepo userRepo;

  public void createUser(User user) {
    userRepo.save(user);
  }

  public void deleteUser(Long id) {
    userRepo.deleteById(id);
  }

  public User getUserById(Long id) {
    return userRepo.findById(id).orElse(null);
  }

  public void updateUser(Long id, User user) {
    var existingUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    existingUser.setName(user.getName());
    existingUser.setEmail(user.getEmail());
    userRepo.save(existingUser);
  }
}
