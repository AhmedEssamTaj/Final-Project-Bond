package com.example.finalprojectbond.Controller;

import com.example.finalprojectbond.Api.ApiResponse;
import com.example.finalprojectbond.InDTO.ExperienceInDTO;
import com.example.finalprojectbond.OutDTO.BriefExplorerOutDTO;
import com.example.finalprojectbond.OutDTO.ExperienceOutDTO;
import com.example.finalprojectbond.Service.ExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping("/get-all")
    public ResponseEntity<List<ExperienceOutDTO>> getAllExperiences() {
        return ResponseEntity.status(200).body(experienceService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createExperience(@RequestBody @Valid ExperienceInDTO experienceInDTO) {
        experienceService.createExperience(experienceInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Experience created successfully"));
    }

    @PutMapping("/update/{experienceId}")
    public ResponseEntity<ApiResponse> updateExperience(@PathVariable Integer experienceId,@RequestBody @Valid ExperienceInDTO experienceInDTO) {
        experienceService.updateExperience(experienceId,experienceInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Experience updated successfully"));
    }

    @DeleteMapping("/delete/{experienceId}")
    public ResponseEntity<ApiResponse> deleteExperience(@PathVariable Integer experienceId) {
        experienceService.deleteExperience(experienceId);
        return ResponseEntity.status(200).body(new ApiResponse("Experience deleted successfully"));
    }

    // ENDPOINT TO FULLY BOOK AN EXPERIENCE
    @PutMapping("/fully-booked/organizer-{organizerId}/experience-{experienceId}")
    public ResponseEntity<ApiResponse> fullyBookedExperience(@PathVariable Integer organizerId ,@PathVariable Integer experienceId) {
        experienceService.changeStatusToFullyBooked(experienceId,organizerId);
        return ResponseEntity.status(200).body(new ApiResponse("Experience is now Fully Booked"));
    }

    // ENDPOINT TO REMOVE EXPLORER FROM EXPERIENCE
    @DeleteMapping("/remove-explorer/organizer-{organizerId}/explorer-{explorerId}/experience-{experienceId}")
    public ResponseEntity<ApiResponse> removeExplorerFromExperience(@PathVariable Integer organizerId,@PathVariable Integer explorerId,@PathVariable Integer experienceId) {
        experienceService.RemoveExplorerFromExperience(organizerId,explorerId,experienceId);
        return ResponseEntity.status(200).body(new ApiResponse("Explorer is now Removed"));
    }

    // ENDPOINT TO GET ALL THE EXPLORER WHO DID NOT CONFIRM THE MEETING ZONE
    @GetMapping("/get-non-confirmed/organizer-{organizerId}/experience-{experienceId}")
    public ResponseEntity<List<BriefExplorerOutDTO>> getAllNonConfirmedExplorersInExperience(@PathVariable Integer organizerId, @PathVariable Integer experienceId) {
       return ResponseEntity.status(200).body(experienceService.getALlNonConfirmedExplorers(organizerId,experienceId));
    }

    // ENDPOINT TO REMOVE ALL EXPLORER WHO DID NOT CONFIRM THE MEETING ZONE
    @DeleteMapping("/remove-non-confirmed/organizer-{organizerId}/experience-{experienceId}")
    public ResponseEntity<ApiResponse> removeAllNonConfirmedExplorersFromExperience(@PathVariable Integer organizerId,@PathVariable Integer experienceId) {
        experienceService.removeAllNotConfirmedExplorersFromExperience(organizerId,experienceId);
        return ResponseEntity.status(200).body(new ApiResponse("All Explorers who did not confirm meeting zone are removed"));
    }


    // ENDPOINT TO GET ALL THE ACCEPTED EXPLORERS IN AN EXPERIENCE
    @GetMapping("/get-accepted/organizer-{organizerId}/experience-{experienceId}")
    public ResponseEntity<List<BriefExplorerOutDTO>> getAllAcceptedExplorersInExperience(@PathVariable Integer organizerId, @PathVariable Integer experienceId) {
        return ResponseEntity.status(200).body(experienceService.getAllAcceptedExplorers(organizerId,experienceId));
    }

    //ENDPOINT TO MAKE THE STATUS OF THE EXPERIENCE COMPLETE
    @PutMapping("/complete/organizer-{organizerId}/experience-{experienceId}")
    public ResponseEntity<ApiResponse> completeExperience(@PathVariable Integer organizerId ,@PathVariable Integer experienceId) {
        experienceService.changeStatusToCompleted(organizerId,experienceId);
        return ResponseEntity.status(200).body(new ApiResponse("Experience is now completed"));
    }

    //ENDPOINT TO CANCEL AN EXPERIENCE
    @PutMapping("/cancel/organizer-{organizerId}/experience-{experienceId}")
    public ResponseEntity<ApiResponse> cancelExperience(@PathVariable Integer organizerId ,@PathVariable Integer experienceId) {
        experienceService.cancelExperience(organizerId,experienceId);
        return ResponseEntity.status(200).body(new ApiResponse("Experience is now completed"));
    }


}
