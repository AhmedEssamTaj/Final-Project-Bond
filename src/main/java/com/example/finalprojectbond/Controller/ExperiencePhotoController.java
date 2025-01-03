package com.example.finalprojectbond.Controller;

import com.example.finalprojectbond.Api.ApiResponse;
import com.example.finalprojectbond.InDTO.ExperiencePhotoInDTO;
import com.example.finalprojectbond.Model.ExperiencePhoto;
import com.example.finalprojectbond.OutDTO.ExperiencePhotoOutDTO;
import com.example.finalprojectbond.Service.ExperiencePhotoService;
import com.example.finalprojectbond.Service.FirebaseStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/experience-photo")
@RequiredArgsConstructor
public class ExperiencePhotoController {

    private final ExperiencePhotoService experiencePhotoService;
    private final FirebaseStorageService firebaseStorageService;

    @GetMapping("/get-all")
    public ResponseEntity<List<ExperiencePhotoOutDTO>> findAll() {
        return ResponseEntity.status(200).body(experiencePhotoService.getAllExperiencePhotos());
    }

    // ENDPOINT TO ADD PHOTOS TO AN EXPERIENCE (SEND IT TO FIREBASE)
    @PostMapping("/add-photo/{organizerId}")
    public ResponseEntity<ApiResponse> addExperiencePhoto(@PathVariable Integer organizerId,@RequestParam("file") MultipartFile file,
                                                          @RequestParam("experienceId") Integer experienceId) throws IOException {
        String fileUrl = firebaseStorageService.uploadFile(file);
        ExperiencePhotoInDTO dto = new ExperiencePhotoInDTO(experienceId, fileUrl);
        experiencePhotoService.addExperiencePhoto(organizerId,dto);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added experience photo"));
    }

    @PutMapping("/update-photo/{experiencePhotoId}")
    public ResponseEntity<ApiResponse> updateExperiencePhoto(@PathVariable Integer experiencePhotoId,@RequestBody @Valid ExperiencePhotoInDTO experiencePhotoInDTO) {
        experiencePhotoService.updateExperiencePhoto(experiencePhotoId, experiencePhotoInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated photo"));
    }

    @DeleteMapping("/delete-photo/{experiencePhotoId}")
    public ResponseEntity<ApiResponse> deleteExperiencePhoto(@PathVariable Integer experiencePhotoId) {
        experiencePhotoService.deleteExperiencePhoto(experiencePhotoId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted photo"));
    }

    // ENDPOINT TO GET ALL PHOTOS OF AN EXPERIENCE
    @GetMapping("/get-experience/{experienceId}")
    public ResponseEntity<List<ExperiencePhotoOutDTO>> getAllExperiencePhotos(@PathVariable Integer experienceId) {
        return ResponseEntity.status(200).body(experiencePhotoService.getExperiencePhotosByExperience(experienceId));
    }



}
