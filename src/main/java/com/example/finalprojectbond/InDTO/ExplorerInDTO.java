package com.example.finalprojectbond.InDTO;

import com.example.finalprojectbond.Model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
public class ExplorerInDTO {

    @Size(max = 25,message = "The length of the user name must be at most 25 characters")
    @NotEmpty(message = "User Name cannot be null")
    private String username;

    @NotEmpty(message = "name cannot be null")
    @Size(max = 50,message = "The length of the name must be at most 50 characters")
    private String name;

    @Size(max = 25,message = "The length of the password must be at most 25 characters")
    @NotEmpty(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.?[A-Z])(?=.?[a-z])(?=.?[0-9])(?=.?[#?!@$%^&*-]).{8,}$",message = "The password must be at least 8" +
            " characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character (e.g., #?!@$%^&*-)" )
    private String password;

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

    @Size(max = 40,message = "The length of the email must be at most 40 characters")
    @NotEmpty(message = "email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Gender cannot be empty")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender must be either Male or Female")
    private String gender;

    @NotEmpty(message = "Phone number cannot be null")
    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone number must start with 05 and be 10 digits")
    private String phoneNumber;

    private String photoURL;

}
