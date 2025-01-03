package com.example.finalprojectbond.Controller;

import com.example.finalprojectbond.InDTO.ApplicationInDTO;
import com.example.finalprojectbond.Service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("/get-my-applications/{organizerId}")
    public ResponseEntity getMyApplications(@PathVariable Integer organizerId) {
        return ResponseEntity.status(200).body(applicationService.getMyApplications(organizerId));
    }

    @PostMapping("/createApplication/{explorerId}/{experienceId}")
    public ResponseEntity createApplication(@PathVariable Integer explorerId,@PathVariable Integer experienceId,@RequestBody @Valid ApplicationInDTO applicationInDTO){
        applicationService.createApplication(explorerId,experienceId,applicationInDTO);
        return ResponseEntity.status(200).body("Application created");
    }

    @PutMapping("//{explorerId}/{experienceId}/{applicationId}")
    public ResponseEntity updateApplication(@PathVariable Integer explorerId,@PathVariable Integer experienceId,@PathVariable Integer applicationId,@RequestBody @Valid ApplicationInDTO applicationInDTO){
        applicationService.updateApplication(explorerId,experienceId,applicationId,applicationInDTO);
        return ResponseEntity.status(200).body("Application updated");
    }

    @DeleteMapping("/cancel-application/{explorerId}/{experienceId}/{applicationId}")
    public ResponseEntity cancelApplication(@PathVariable Integer explorerId,@PathVariable Integer experienceId,@PathVariable Integer applicationId){
        applicationService.cancelApplication(explorerId,experienceId,applicationId);
        return ResponseEntity.status(200).body("Application cancelled");
    }

    @PutMapping("/reject-application/{organizerId}/{explorerId}/{applicationId}")
    public ResponseEntity rejectApplication(@PathVariable Integer organizerId,@PathVariable Integer explorerId,@PathVariable Integer applicationId) {
        applicationService.rejectApplication(organizerId,explorerId,applicationId);
        return ResponseEntity.status(200).body("Application rejected");
    }
    @PutMapping("/accept-application/{organizerId}/{explorerId}/{applicationId}")
    public ResponseEntity acceptApplication(@PathVariable Integer organizerId,@PathVariable Integer explorerId,@PathVariable Integer applicationId) {
        applicationService.acceptApplication(organizerId,explorerId,applicationId);
        return ResponseEntity.status(200).body("Application accepted");
    }

    @GetMapping("/get-applications-by-experience/{organizerId}/{experienceId}")
    public ResponseEntity getApplicationsByExperience(@PathVariable Integer organizerId,@PathVariable Integer experienceId) {
        return ResponseEntity.status(200).body(applicationService.getApplicationsByExperience(organizerId,experienceId));
    }

    @GetMapping("/get-completed-applications/{organizerId}/{experienceId}")
    public ResponseEntity getCompletedApplications(@PathVariable Integer organizerId,@PathVariable Integer experienceId) {
        return ResponseEntity.status(200).body(applicationService.getCompletedApplications(organizerId,experienceId));
    }

    @GetMapping("/get-pending-applications/{organizerId}/{experienceId}")
    public ResponseEntity getPendingApplications(@PathVariable Integer organizerId,@PathVariable Integer experienceId) {
        return ResponseEntity.status(200).body(applicationService.getPendingApplications(organizerId,experienceId));
    }

    @GetMapping("/search-applications-by-experience-title/{explorerId}/{title}")
    public ResponseEntity searchApplicationsByExperienceTitle(@PathVariable Integer explorerId,@PathVariable String title){
        return ResponseEntity.status(200).body(applicationService.searchApplicationsByExperienceTitle(explorerId,title));
    }
}
