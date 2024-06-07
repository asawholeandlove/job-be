package com.example.jobhunter.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobhunter.model.Company;
import com.example.jobhunter.model.dto.ResultPaginationDTO;
import com.example.jobhunter.service.CompanyService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
  private final CompanyService companyService;

  // Get all companies
  @GetMapping
  public ResponseEntity<ResultPaginationDTO> getCompanies(@RequestParam("current") Optional<String> currentOptional,
      @RequestParam("pageSize") Optional<String> pageSizeOptional) {
    String sCurrent = currentOptional.orElse("");
    String sPageSize = pageSizeOptional.orElse("");

    Pageable pageable = PageRequest.of(Integer.parseInt(sCurrent) - 1, Integer.parseInt(sPageSize));

    var result = companyService.getCompanies(pageable);
    return ResponseEntity.ok(result);
  }

  // Create a new company
  @PostMapping
  public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {
    return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(company));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Company> updateCompany(@PathVariable Long id, @Valid @RequestBody Company company) {
    var updateCompany = companyService.updateCompany(id, company);
    return ResponseEntity.ok(updateCompany);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
    companyService.deleteCompany(id);
    return ResponseEntity.ok(null);
  }
}
