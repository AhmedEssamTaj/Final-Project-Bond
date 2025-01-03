package com.example.finalprojectbond.OutDTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ExperiencePhotoOutDTO {
    private String photoUrl;
}
