package com.example.jobhunter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobhunter.model.dto.LoginDTO;
import com.example.jobhunter.model.dto.ResLoginDTO;
import com.example.jobhunter.util.SecurityUtil;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final SecurityUtil securityUtil;

  @PostMapping("/login")
  public ResponseEntity<ResLoginDTO> login(@RequestBody LoginDTO loginDto) {
    // Nạp input gồm username/password vào Security
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        loginDto.getUsername(), loginDto.getPassword());

    // xác thực người dùng => cần viết hàm loadUserByUsername
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

    // create a token
    String access_token = this.securityUtil.createToken(authentication);
    ResLoginDTO res = new ResLoginDTO();
    res.setAccessToken(access_token);
    return ResponseEntity.ok().body(res);
  }
}
