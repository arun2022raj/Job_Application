package com.embarkx.reviewms.controller;

import com.embarkx.reviewms.model.Review;
import com.embarkx.reviewms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        System.out.println(reviewService.getAllReviews(companyId));
        return new ResponseEntity<>(reviewService.getAllReviews(companyId),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                            @RequestBody Review review) {
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if (isReviewSaved)
            return new ResponseEntity<>("Review Added Successfully",
                    HttpStatus.OK);
        else
            return new ResponseEntity<>(
                    "Review Not Saved",
                    HttpStatus.NOT_FOUND);
    }

    @GetMapping ("/{reviewId}")
    public ResponseEntity<Review> getReview (@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(reviewId ),
        HttpStatus.OK);
    }

    @PutMapping ("/{reviewId}")
    public ResponseEntity<String> updateReview (
                                                @PathVariable Long reviewId,
                                                @RequestBody Review review) {
        boolean isReviewUpdated = reviewService.updateReview(
                reviewId, review);
        if (isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully",
                HttpStatus.OK);
     else
        return new ResponseEntity<>("Review not updated",
                HttpStatus.NOT_FOUND);
    }
    @DeleteMapping ("/{reviewId}")
    public ResponseEntity<String> deleteReview (@PathVariable Long reviewId) {
        boolean isReviewDeleted =reviewService.deleteReview(reviewId);
        if (isReviewDeleted)
            return new ResponseEntity<>("Review deleted successfully",
                HttpStatus.OK);
     else
        return new ResponseEntity<>("Review not deleted",
                HttpStatus.NOT_FOUND);
    }


}
