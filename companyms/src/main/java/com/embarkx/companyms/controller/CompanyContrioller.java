package com.embarkx.companyms.controller;

import com.embarkx.companyms.model.Company;
import com.embarkx.companyms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyContrioller {

    @Autowired
    private  CompanyService companyService;

    @GetMapping("/getAllCompany")
    public ResponseEntity<List<Company>> getAllCompany(){
        return new ResponseEntity<>(companyService.getAllCompany(), HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Company> findById(@PathVariable long id){
        return new ResponseEntity<>(companyService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Company> save(@RequestBody Company company){
        return new ResponseEntity<>(companyService.save(company), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestParam long id, @RequestBody Company company){
        return new ResponseEntity<>(companyService.update(id,company), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        return new ResponseEntity<>(companyService.deleteById(id), HttpStatus.OK);
    }
}
