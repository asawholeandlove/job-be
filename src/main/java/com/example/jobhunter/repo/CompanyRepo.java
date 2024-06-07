package com.example.jobhunter.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobhunter.model.Company;

public interface CompanyRepo extends JpaRepository<Company, Long> {

}
