package com.example.jobhunter.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.jobhunter.model.User;
import com.example.jobhunter.model.dto.Meta;
import com.example.jobhunter.model.dto.ResultPaginationDTO;
import com.example.jobhunter.repo.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepo userRepo;

  public User createUser(User user) {
    return userRepo.save(user);
  }

  public void deleteUser(Long id) {
    userRepo.deleteById(id);
  }

  public ResultPaginationDTO getAllUsers(Pageable pageable) {
    Page<User> pageUser = userRepo.findAll(pageable);

    var meta = new Meta();

    meta.setPage(pageUser.getNumber() + 1);
    meta.setPageSize(pageUser.getSize());
    meta.setPages(pageUser.getTotalPages());
    meta.setTotal(pageUser.getTotalElements());

    ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();

    resultPaginationDTO.setMeta(meta);
    resultPaginationDTO.setResult(pageUser.getContent());

    return resultPaginationDTO;
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

  public User getUserByEmail(String email) {
    return userRepo.findByEmail(email);
  }
}
