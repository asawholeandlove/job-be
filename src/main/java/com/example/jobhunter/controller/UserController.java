package com.example.jobhunter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobhunter.model.User;
import com.example.jobhunter.model.dto.ResultPaginationDTO;
import com.example.jobhunter.service.UserService;
import com.example.jobhunter.service.error.IdInvalidException;
import com.example.jobhunter.util.annotation.ApiMessage;
import com.turkraft.springfilter.boot.Filter;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @ApiMessage("Get all users")
  @GetMapping
  public ResponseEntity<ResultPaginationDTO> getUsers(
      @Filter Specification<User> spec,
      Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(
        this.userService.getAllUsers(spec, pageable));
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    var hashPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(hashPassword);
    var newUser = userService.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

  @PutMapping("/{id}")
  public String updateUser(@PathVariable Long id, @RequestBody User user) {
    userService.updateUser(id, user);
    return "User updated: ";
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable Long id) throws IdInvalidException {
    if (id > 1000) {
      throw new IdInvalidException("Id quá lớn");
    }
    userService.deleteUser(id);
    return "User deleted: ";
  }

}
