package com.embarkx.companyms.service.impl;

import com.embarkx.companyms.model.Company;
import com.embarkx.companyms.repository.CompanyRepository;
import com.embarkx.companyms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public List<Company> getAllCompany() {
        return  companyRepository.findAll();

    }

    @Override
    public Company findById(long id) {
        return  companyRepository.findById(id).orElseThrow(()->new RuntimeException("notfound"));
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public boolean update(long id, Company updateCompany) {
        Optional<Company> jobOptional = companyRepository.findById(id);
        if (jobOptional.isPresent()) {
            Company company = jobOptional.get();
            company.setDescription(updateCompany.getDescription());
            company.setName(updateCompany.getName());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteById(long id) {
        try {
            companyRepository.deleteById(id);
        }catch (Exception exception){
            return false;
        }
        return true;
    }
}
