package com.example.finalprojectbond.InDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ExperiencePhotoInDTO {

    @NotNull(message = "experience id cannot be empty")
    private Integer experienceId;

    private String photoUrl;

}
