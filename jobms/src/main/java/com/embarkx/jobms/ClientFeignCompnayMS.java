package com.embarkx.jobms;


import com.embarkx.jobms.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="COMPANYMS")// ,url = "http://127.0.0.1:8081/"
public interface ClientFeignCompnayMS {

    @GetMapping("companies/getbyid/{id}")
    public Company findById(@PathVariable long id);

}
