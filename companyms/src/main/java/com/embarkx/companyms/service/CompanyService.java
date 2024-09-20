package com.embarkx.companyms.service;

import com.embarkx.companyms.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompany();

    Company findById(long id);

    Company save(Company company);

    boolean update(long id, Company company);

    Boolean deleteById(long id);
}
