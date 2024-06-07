package com.example.jobhunter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobhunter.model.Company;
import com.example.jobhunter.service.CompanyService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
  private final CompanyService companyService;

  // Get all companies
  @GetMapping
  public List<Company> getCompanies() {
    return companyService.getCompanies();
  }

  // Create a new company
  @PostMapping
  public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {
    return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(company));
  }
}
