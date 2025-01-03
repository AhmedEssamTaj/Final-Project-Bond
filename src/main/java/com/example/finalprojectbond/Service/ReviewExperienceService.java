package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Api.ApiException;
import com.example.finalprojectbond.Model.Experience;
import com.example.finalprojectbond.Model.Organizer;
import com.example.finalprojectbond.Model.ReviewExperience;
import com.example.finalprojectbond.Repository.ExperienceRepository;
import com.example.finalprojectbond.Repository.OrganizerRepository;
import com.example.finalprojectbond.Repository.ReviewExperienceRepository;
import com.example.finalprojectbond.Repository.ReviewExplorerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewExperienceService {
    private final ReviewExperienceRepository reviewExperienceRepository;
    private final ExperienceRepository experienceRepository;
    private final OrganizerRepository organizerRepository;

    public List<ReviewExperience> getAllReviews() {
        return reviewExperienceRepository.findAll();
    }

    public List<ReviewExperience> getReviewsByExperience(Integer experienceId) {
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if(experience==null) {
            new ApiException("Experience not found");
        }

        List<ReviewExperience> reviews = reviewExperienceRepository.findByExperience(experience);
        if (reviews.isEmpty()) {
            throw new ApiException("No reviews found for this experience");
        }

        return reviews;
    }

    public void createReview(Integer organizerId, Integer experienceId, ReviewExperience reviewExperience) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to create reviews");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if(experience==null) {
            new ApiException("Experience not found");
        }
        reviewExperience.setExperience(experience);
        reviewExperienceRepository.save(reviewExperience);
    }

    public void updateReview(Integer organizerId, Integer reviewId, ReviewExperience reviewExperience) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to update reviews");
        }

        ReviewExperience existingReview = reviewExperienceRepository.findReviewExperienceById(reviewId);
        if(existingReview==null){
            new ApiException("Review not found");
        }

        existingReview.setComment(reviewExperience.getComment());
        existingReview.setRating(reviewExperience.getRating());

        reviewExperienceRepository.save(existingReview);
    }

    public void deleteReview(Integer organizerId, Integer reviewId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to delete reviews");
        }

        ReviewExperience review = reviewExperienceRepository.findReviewExperienceById(reviewId);
        if(review==null){
           throw  new ApiException("Review not found");
        }

        reviewExperienceRepository.delete(review);
    }






}
