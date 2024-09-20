package com.embarkx.jobms.service.impl;

import com.embarkx.jobms.ClientFeignCompnayMS;
import com.embarkx.jobms.ClientFeignReviewMS;
import com.embarkx.jobms.dto.JobWithCompanyDto;
import com.embarkx.jobms.external.Company;
import com.embarkx.jobms.external.Review;
import com.embarkx.jobms.model.Job;
import com.embarkx.jobms.repository.JobRepository;
import com.embarkx.jobms.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private  final JobRepository jobRepository;
    private final RestTemplate restTemplate;
    private final ClientFeignReviewMS clientFeignReviewMS;
    private final ClientFeignCompnayMS clientFeignCompnayMS;

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public JobWithCompanyDto getJobById(int id) {
        System.out.println("hiiii");
        Job job =jobRepository.findById(id).get();
        Company company = restTemplate.getForObject("http://COMPANYMS/getbyid/"+job.getCompanyId(), Company.class);
        JobWithCompanyDto jobWithCompanyDto = new JobWithCompanyDto(job);

        jobWithCompanyDto.setCompany(company);
        return jobWithCompanyDto;
    }

    @Override
    public boolean deleteJobById(int id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJobById(int id, Job updateJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updateJob.getTitle());
            job.setDescription(updateJob.getDescription());
            job.setMinSalary(updateJob.getMinSalary());
            job.setMaxSalary(updateJob.getMaxSalary());
            job.setLocation(updateJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity<List<JobWithCompanyDto>> findAll() {
        List<Job> jobs=  jobRepository.findAll();

        List<JobWithCompanyDto> jobWithCompanyDtos= jobs.stream().map((job)->getJobWithCompanyDto(job)).collect(Collectors.toList());

        return new ResponseEntity(jobWithCompanyDtos, HttpStatus.OK);
    }

    public JobWithCompanyDto getJobWithCompanyDto(Job job){
//        Company company = restTemplate.getForObject("http://COMPANYMS/getbyid/"+job.getCompanyId(), Company.class);
        Company company = clientFeignCompnayMS.findById(job.getCompanyId());
//        Review[] reviews = restTemplate.getForObject("http://REVIEWMS/review?companyId="+job.getCompanyId(), Review[].class);
            List<Review> reviews= clientFeignReviewMS.getAllReviews(job.getCompanyId());
//        System.out.println(reviews);
        JobWithCompanyDto jobWithCompanyDto = new JobWithCompanyDto(job);
        jobWithCompanyDto.setCompany(company);
//        jobWithCompanyDto.setReview(Arrays.stream(reviews).toList());
        jobWithCompanyDto.setReview(reviews);

        System.out.println(jobWithCompanyDto);
        return jobWithCompanyDto;
    }
}
