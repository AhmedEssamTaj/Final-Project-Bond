package com.example.finalprojectbond.OutDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class ExperienceSearchOutDTO {

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private String status;


}
