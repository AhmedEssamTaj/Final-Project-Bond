package com.example.finalprojectbond.Controller;

import com.example.finalprojectbond.Model.ReviewExperience;
import com.example.finalprojectbond.Service.ReviewExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review-experience")
@RequiredArgsConstructor
public class ReviewExperienceController {

        private final ReviewExperienceService reviewExperienceService;

        @GetMapping("/getAll/{experienceId}")
        public ResponseEntity getAllReviewsByExperience(@PathVariable Integer experienceId) {
            return ResponseEntity.status(200).body(reviewExperienceService.getReviewsByExperience(experienceId));
        }

        @PostMapping("/add")
        public ResponseEntity createReview(@RequestParam Integer organizerId, @RequestParam Integer experienceId, @RequestBody @Valid ReviewExperience reviewExperience) {
            reviewExperienceService.createReview(organizerId, experienceId, reviewExperience);
            return ResponseEntity.status(200).body("Review added successfully");
        }

        @PutMapping("/update/{reviewId}")
        public ResponseEntity updateReview(@PathVariable Integer reviewId, @RequestParam Integer organizerId, @RequestBody @Valid ReviewExperience reviewExperience) {
            reviewExperienceService.updateReview(organizerId, reviewId, reviewExperience);
            return ResponseEntity.status(200).body("Review updated successfully");
        }

        @DeleteMapping("/delete/{reviewId}")
        public ResponseEntity deleteReview(@PathVariable Integer reviewId, @RequestParam Integer organizerId) {
            reviewExperienceService.deleteReview(organizerId, reviewId);
            return ResponseEntity.status(200).body("Review deleted successfully");
        }


    }

