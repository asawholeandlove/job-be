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

  public Company updateCompany(Long id, Company c) {
    var companyOptional = this.companyRepo.findById(id);
    if (companyOptional.isPresent()) {
      Company currentCompany = companyOptional.get();
      currentCompany.setLogo(c.getLogo());
      currentCompany.setName(c.getName());
      currentCompany.setDescription(c.getDescription());
      currentCompany.setAddress(c.getAddress());
      return this.companyRepo.save(currentCompany);
    }
    return null;
  }

}
