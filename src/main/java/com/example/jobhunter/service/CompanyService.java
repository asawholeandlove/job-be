package com.example.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.jobhunter.model.Company;
import com.example.jobhunter.model.User;
import com.example.jobhunter.model.dto.Meta;
import com.example.jobhunter.model.dto.ResultPaginationDTO;
import com.example.jobhunter.repo.CompanyRepo;

import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {
  private final CompanyRepo companyRepo;

  // Get all companies with pagination
  public ResultPaginationDTO getCompanies(Pageable pageable) {
    Page<Company> pageCompany = companyRepo.findAll(pageable);

    var meta = new Meta();

    meta.setPage(pageCompany.getNumber() + 1);
    meta.setPageSize(pageCompany.getSize());
    meta.setPages(pageCompany.getTotalPages());
    meta.setTotal(pageCompany.getTotalElements());

    ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();

    resultPaginationDTO.setMeta(meta);
    resultPaginationDTO.setResult(pageCompany.getContent());

    return resultPaginationDTO;

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
