package com.example.finalprojectbond.Controller;

import com.example.finalprojectbond.Api.ApiResponse;
import com.example.finalprojectbond.InDTO.OrganizerInDTO;
import com.example.finalprojectbond.Service.OrganizerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organizer")
@RequiredArgsConstructor
public class OrganizerController {
    private final OrganizerService organizerService;

    @GetMapping("/get-my-organizer/{organizerId}")
    public ResponseEntity getMyOrganizer(@PathVariable Integer organizerId){
        return ResponseEntity.status(200).body(organizerService.getMyOrganizer(organizerId));
    }

    @PostMapping("/register-organizer")
    public ResponseEntity registerOrganizer(@RequestBody @Valid OrganizerInDTO organizerInDTO) {
        organizerService.registerOrganizer(organizerInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("organizer registered"));
    }

    @PutMapping("/update-organizer/{organizer_id}")
    public ResponseEntity updateOrganizer(@PathVariable Integer organizer_id,@RequestBody @Valid OrganizerInDTO organizerInDTO) {
        organizerService.updateOrganizer(organizer_id,organizerInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("organizer updated"));
    }

    @DeleteMapping("/delete-my-organizer/{organizer_id}")
    public ResponseEntity deleteMyOrganizer(@PathVariable Integer organizer_id) {
        organizerService.deleteMyOrganizer(organizer_id);
        return ResponseEntity.status(200).body(new ApiResponse("organizer deleted"));
    }

    @GetMapping("/get-organizer-by-rating-asc")
    public ResponseEntity getOrganizerByRatingAsc(){
        return ResponseEntity.status(200).body(organizerService.getOrganizerByRatingAsc());
    }

    @GetMapping("/get-organizer-by-rating-desc")
    public ResponseEntity getOrganizerByRatingDesc(){
        return ResponseEntity.status(200).body(organizerService.getOrganizerByRatingDesc());
    }

    @GetMapping("/get-organizer-by-city/{city}")
    public ResponseEntity getOrganizerByCity(@PathVariable String city){
        return ResponseEntity.status(200).body(organizerService.getOrganizerByCity(city));
    }

    @GetMapping("/get-all-organizer-experiences/{organizerId}")
    public ResponseEntity getAllOrganizerExperiences(@PathVariable Integer organizerId){
        return ResponseEntity.status(200).body(organizerService.getAllOrganizerExperiences(organizerId));
    }

    @GetMapping("/search-experience-by-title/{organizerId}/{title}")
    public ResponseEntity searchExperienceByTitle(@PathVariable Integer organizerId,@PathVariable String title){
        return ResponseEntity.status(200).body(organizerService.searchExperienceByTitle(organizerId,title));
    }

}
