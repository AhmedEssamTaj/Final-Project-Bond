package com.example.finalprojectbond.Controller;

import com.example.finalprojectbond.Api.ApiResponse;
import com.example.finalprojectbond.Model.ReviewExplorer;
import com.example.finalprojectbond.Repository.ReviewExplorerRepository;
import com.example.finalprojectbond.Service.ReviewExperienceService;
import com.example.finalprojectbond.Service.ReviewExplorerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review-explorer")
@RequiredArgsConstructor
public class ReviewExplorerController {

    private final ReviewExplorerService reviewExplorerService;

    //3
    @GetMapping("/getAll/{explorerId}")
    public ResponseEntity getAllReviewsByExplorer(@PathVariable Integer explorerId) {
        return ResponseEntity.status(200).body(reviewExplorerService.getAllReviewsByExplorer(explorerId));
    }

    @PostMapping("/add")
    public ResponseEntity createReview(@RequestParam Integer organizerId, @RequestParam Integer explorerId, @RequestBody @Valid ReviewExplorer reviewExplorer) {
        reviewExplorerService.createReview(organizerId, explorerId, reviewExplorer);
        return ResponseEntity.status(200).body("Review added successfully");
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity updateReview(@PathVariable Integer reviewId, @RequestParam Integer organizerId, @RequestBody @Valid ReviewExplorer reviewExplorer) {
        reviewExplorerService.updateReview(organizerId, reviewId, reviewExplorer);
        return ResponseEntity.status(200).body("Review updated successfully");
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable Integer reviewId, @RequestParam Integer organizerId) {
        reviewExplorerService.deleteReview(organizerId, reviewId);
        return ResponseEntity.status(200).body("Review deleted successfully");
    }

    //2
    @GetMapping("/getAllByOrganizer/{organizerId}")
    public ResponseEntity getReviewsByOrganizer(@PathVariable Integer organizerId) {
        return ResponseEntity.status(200).body(reviewExplorerService.getReviewsByOrganizer(organizerId));
    }

    //4
    @GetMapping("/getOrganizerReviewsHighToLow/{organizerId}")
    public ResponseEntity getOrganizerReviewsFilteredByHighToLow(@PathVariable Integer organizerId) {
        return ResponseEntity.status(200).body(reviewExplorerService.getOrganizerReviewsFilteredByHighToLow(organizerId));
    }

    //5
    @GetMapping("/getOrganizerReviewsLowToHigh/{organizerId}")
    public ResponseEntity getOrganizerReviewsFilteredByLowToHigh(@PathVariable Integer organizerId) {
        return ResponseEntity.status(200).body(reviewExplorerService.getOrganizerReviewsFilteredByLowToHigh(organizerId));
    }

    //6
    @GetMapping("/getReviewsByExplorer/{explorerId}")
    public ResponseEntity getReviewsByExplorer(@PathVariable Integer explorerId) {
        List<ReviewExplorer> reviews = reviewExplorerService.getReviewsByExplorer(explorerId);
        return ResponseEntity.status(200).body(reviews);
    }



}
