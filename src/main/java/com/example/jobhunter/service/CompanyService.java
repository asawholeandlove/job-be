package com.example.jobhunter.service;

import org.springframework.stereotype.Service;

import com.example.jobhunter.model.Company;
import com.example.jobhunter.repo.CompanyRepo;

import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {
  private final CompanyRepo companyRepo;

  // Get all companies
  public List<Company> getCompanies() {
    return companyRepo.findAll();
  }

  // Create a new company
  public Company createCompany(Company company) {
    return companyRepo.save(company);
  }

  public void deleteCompany(Long id) {
    companyRepo.deleteById(id);
  }

}
