package com.example.jobhunter.model;

import java.time.Instant;

import com.example.jobhunter.util.SecurityUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
@Table(name = "companies")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank(message = "Tên công ty không được để trống")
  private String name;

  @Column(columnDefinition = "MEDIUMTEXT")
  private String description;

  private String address;

  private String logo;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
  private Instant createdAt;

  private Instant updatedAt;

  private String createdBy;

  private String updatedBy;

  @PrePersist
  public void onCreate() {
    this.createdBy = SecurityUtil.getCurrentUserLogin().orElse("");
    this.createdAt = Instant.now();
  }
}
