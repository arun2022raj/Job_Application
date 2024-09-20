package com.embarkx.jobms;


import com.embarkx.jobms.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name ="REVIEWMS")//,url = "http://127.0.0.1:8083/review"
public interface ClientFeignReviewMS {

    @GetMapping("/reviews")
    List<Review> getAllReviews(@RequestParam Long companyId);

}
