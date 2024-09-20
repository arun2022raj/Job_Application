package com.embarkx.jobms.service;

import com.embarkx.jobms.dto.JobWithCompanyDto;
import com.embarkx.jobms.model.Job;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface JobService
{
    Job createJob(Job job);
    JobWithCompanyDto getJobById(int id);
    boolean deleteJobById(int id);
    boolean updateJobById(int id,Job updateJob);

    ResponseEntity<List<JobWithCompanyDto>> findAll();
}
