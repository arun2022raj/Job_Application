package com.embarkx.reviewms.service.impl;

import com.embarkx.reviewms.model.Review;
import com.embarkx.reviewms.repository.ReviewRepository;
import com.embarkx.reviewms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if (companyId!= null && review!=null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReview(Long reviewId) {
      return  reviewRepository.findById(reviewId).orElseThrow(()->new RuntimeException("notfound"));


    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedreview) {
        Review review = reviewRepository.findById(reviewId) .orElse (  null);
        if (review!=null){
            review. setTitle (updatedreview.getTitle());
            review.setDescription(updatedreview.getDescription());
            review. setRating( updatedreview.getRating());
            review. setCompanyId(updatedreview.getCompanyId());
                    reviewRepository.save(updatedreview);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            reviewRepository.delete(review);
            return true;
        } else {
            return false;
        }
    }
}






//    @Override
//    public List<Review> getAllReview() {
//        return  reviewRespository.findAll();
//
//    }
//
//    @Override
//    public List<Review> getAllReview(Long companyId) {
//        return null;
//    }
//
//    @Override
//    public Review findById(long id) {
//        return  reviewRespository.findById(id).orElseThrow(()->new RuntimeException("notfound"));
//    }
//
//    @Override
//    public Review save(Review company) {
//        return reviewRespository.save(company);
//    }
//
//    @Override
//    public Boolean update(long id, Review updateReview) {
//        Optional<Review> jobOptional = reviewRespository.findById(id);
//        if (jobOptional.isPresent()) {
//            Review review = jobOptional.get();
//            review.setCompanyId(updateReview.getCompanyId());
//            review.setTitle(updateReview.getTitle());
//            review.setDescription(updateReview.getDescription());
//            review.setRating(review.getRating());
//            reviewRespository.save(review);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean deleteById(long id) {
//        try {
//            reviewRespository.deleteById(id);
//        }catch (Exception exception){
//            return false;
//        }
//        return true;
//    }

