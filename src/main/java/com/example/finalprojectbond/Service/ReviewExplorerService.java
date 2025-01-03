package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Api.ApiException;
import com.example.finalprojectbond.Model.Experience;
import com.example.finalprojectbond.Model.Explorer;
import com.example.finalprojectbond.Model.Organizer;
import com.example.finalprojectbond.Model.ReviewExplorer;
import com.example.finalprojectbond.Repository.ExperienceRepository;
import com.example.finalprojectbond.Repository.ExplorerRepository;
import com.example.finalprojectbond.Repository.OrganizerRepository;
import com.example.finalprojectbond.Repository.ReviewExplorerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewExplorerService {


    private final ReviewExplorerRepository reviewExplorerRepository;
    private final ExplorerRepository explorerRepository;
    private final OrganizerRepository organizerRepository;

    public List<ReviewExplorer> getAllReviews() {
        return reviewExplorerRepository.findAll();
    }


    public void createReview(Integer organizerId, Integer explorerId, ReviewExplorer reviewExplorer) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to create reviews");
        }

        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer was not found");
        }

        reviewExplorer.setOrganizer(organizer);
        reviewExplorer.setExplorer(explorer);
        reviewExplorerRepository.save(reviewExplorer);
    }

    public void updateReview(Integer organizerId, Integer reviewId, ReviewExplorer reviewExplorer) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to update reviews");
        }

        ReviewExplorer existingReview = reviewExplorerRepository.findReviewExplorerById(reviewId);
        if(existingReview==null){
            new ApiException("Review was not found");
        }
        existingReview.setComment(reviewExplorer.getComment());
        existingReview.setRating(reviewExplorer.getRating());

        reviewExplorerRepository.save(existingReview);
    }

    public void deleteReview(Integer organizerId, Integer reviewId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to delete reviews");
        }
        ReviewExplorer review = reviewExplorerRepository.findReviewExplorerById(reviewId);
        if(review==null){
            new ApiException("Review was not found");
        }
        reviewExplorerRepository.delete(review);
    }


    //9
    public List<ReviewExplorer> getReviewsByOrganizer(Integer organizerId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer was not found");
        }

        List<ReviewExplorer> reviews = reviewExplorerRepository.findByOrganizer(organizer);
        if (reviews.isEmpty()) {
            throw new ApiException("No reviews found for this organizer");
        }

        return reviews;
    }

    // 10 Endpoint 34: Get all reviews for an explorer
    public List<ReviewExplorer> getAllReviewsByExplorer(Integer explorerId) {
        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer not found");
        }
        return reviewExplorerRepository.findByExplorer(explorer);
    }

    // Endpoint 16: Filter organizer reviews by high to low rating
    public List<ReviewExplorer> getOrganizerReviewsFilteredByHighToLow(Integer organizerId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        return reviewExplorerRepository.findByOrganizerOrderByRatingDesc(organizer);
    }

    // Endpoint 17: Filter organizer reviews by low to high rating
    public List<ReviewExplorer> getOrganizerReviewsFilteredByLowToHigh(Integer organizerId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        return reviewExplorerRepository.findByOrganizerOrderByRatingAsc(organizer);
    }


    public List<ReviewExplorer> getReviewsByExplorer(Integer explorerId) {
        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer was not found");
        }

        List<ReviewExplorer> reviews = reviewExplorerRepository.findByExplorer(explorer);
        if (reviews.isEmpty()) {
            throw new ApiException("No reviews found for this explorer");
        }

        return reviews;
    }


}
