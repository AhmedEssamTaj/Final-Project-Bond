package com.example.finalprojectbond.InDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class OrganizerInDTO {
    @Size(max = 25,message = "The length of the user name must be at most 25 characters")
    @NotEmpty(message = "User Name cannot be null")
    private String username;

    @Size(max = 25,message = "The length of the password must be at most 25 characters")
    @NotEmpty(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.?[A-Z])(?=.?[a-z])(?=.?[0-9])(?=.?[#?!@$%^&*-]).{8,}$",message = "The password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character (e.g., #?!@$%^&*-)")
    private String password;

    @Size(max = 30,message = "The length of the user name must be at most 30 characters")
    @NotEmpty(message = "Name cannot be null")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @Positive(message = "Age cannot be null ")
    @Min(value = 18, message = "Age must be at least 18 ")
    @Max(value = 100, message = "Age cannot be more than 100 ")
    private Integer age;

    @Size(max = 30,message = "The length of the city must be at most 30 characters")
    @NotEmpty(message = "City cannot be null")
    private String city;

    @Size(max = 70,message = "The length of the health status must be at most 70 characters")
    @NotEmpty(message = "health status cannot be null")
    private String healthStatus;

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

    private String photoURL;

    @Size(max = 255,message = "The length of the user profile summary must be at most 255 characters")
    @NotEmpty(message = "User Profile Summary cannot be null")
    private String userProfileSummary;

    @Size(max = 20,message = "The length of the license serial number must be at most 20 characters")
    @NotEmpty(message = "license serial number cannot be null")
    private String licenseSerialNumber;


}
