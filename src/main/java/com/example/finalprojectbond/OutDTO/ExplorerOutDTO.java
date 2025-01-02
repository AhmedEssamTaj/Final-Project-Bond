package com.example.finalprojectbond.OutDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ExplorerOutDTO {
    @Size(max = 25,message = "The length of the user name must be at most 25 characters")
    @NotEmpty(message = "User Name cannot be null")
    private String username;

    @Positive(message = "Age cannot be null ")
    @Min(value = 18, message = "Age must be at least 18 ")
    @Max(value = 100, message = "Age cannot be more than 100 ")
    private Integer age;

    @Size(max = 40,message = "The length of the city must be at most 40 characters")
    @NotEmpty(message = "City cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Gender cannot be empty")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender must be either Male or Female")
    private String gender;


    @NotEmpty(message = "Phone number cannot be null")
    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone number must start with 05 and be 10 digits")
    private String phoneNumber;


}
