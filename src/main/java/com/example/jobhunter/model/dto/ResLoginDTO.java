package com.example.jobhunter.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class ResLoginDTO {
  private String accessToken;
  private UserInfo user;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class UserInfo {
    private long id;
    private String email;
    private String name;
  }
}
