package com.embarkx.jobms.dto;

import com.embarkx.jobms.external.Company;

import com.embarkx.jobms.external.Review;
import com.embarkx.jobms.model.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobWithCompanyDto {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private  String location;
    private Company company;
    private List<Review> review;

     public JobWithCompanyDto(Job job){
         this.id=job.getId();
         this.location=job.getLocation();
         this.title= job.getTitle();
         this.description= job.getDescription();
         this.minSalary= job.getMinSalary();
         this.maxSalary=job.getMaxSalary();
     }

}
