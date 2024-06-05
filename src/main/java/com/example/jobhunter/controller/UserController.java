package com.example.jobhunter.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.jobhunter.model.User;
import com.example.jobhunter.service.UserService;
import com.example.jobhunter.service.error.IdInvalidException;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

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

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @GetMapping
  public ResponseEntity<User> home() {
    var newUser = new User();
    newUser.setEmail("admin");
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }
}
