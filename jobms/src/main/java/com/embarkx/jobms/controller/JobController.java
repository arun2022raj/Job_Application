package com.embarkx.jobms.controller;

import com.embarkx.jobms.dto.JobWithCompanyDto;
import com.embarkx.jobms.model.Job;
import com.embarkx.jobms.service.JobService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("jobsser")
public class JobController
{

    private final JobService jobService;

    int attempt=0;

    @PostMapping ("/savejob")
    public Job createJob(@Valid @RequestBody Job job) {
        return jobService.createJob(job);
    }

    @GetMapping("jobs")
    @CircuitBreaker(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
    public ResponseEntity<List<JobWithCompanyDto>> findAll(){
        System.out.println("attempt"+ ++attempt);
        return jobService.findAll();

    }
    public ResponseEntity<List<String>> companyBreakerFallback(Exception exception){
        return new ResponseEntity<>( Arrays.asList("India"), HttpStatus.OK) ;
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<JobWithCompanyDto> getJobById(@PathVariable int id) {

        return new ResponseEntity<>( jobService.getJobById(id), HttpStatus.OK) ;
    }

    @DeleteMapping("deletebyid/{id}")
    public boolean deleteJobById(@PathVariable int id) {
        return jobService.deleteJobById(id);
    }

    @PutMapping("/update")
    public boolean updateJobById(@RequestParam int id,@RequestBody Job updateJob) {
        return jobService.updateJobById(id,updateJob);
    }
}
