package com.example.finalprojectbond.OutDTO;

import com.example.finalprojectbond.Model.ExperiencePhoto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
public class ExperienceOutDTO {

    private String title;
    private String description;
    private String city;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String difficulty;
    private String audienceType;
    private List<ExperiencePhotoOutDTO> photos;

}
