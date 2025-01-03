package com.example.finalprojectbond.OutDTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    private LocalDate createdAt;

    private String difficulty;
}
