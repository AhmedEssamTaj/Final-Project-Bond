package com.example.finalprojectbond.InDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ApplicationInDTO {
    @Size(max = 255,message = "The length of the description must be at most 255 characters")
    @NotEmpty(message = "Description cannot be null")
    private String description;

    @Size(max = 120,message = "The length of the tools must be at most 120 characters")
    @NotEmpty(message = "Tools cannot be null")
    private String tools;



}
