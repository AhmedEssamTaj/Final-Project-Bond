package com.example.finalprojectbond.OutDTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ExplorerOutDTO {

    private String name;
    private Integer age;
    private String city;
    private String healthStatus;
    private String email;
    private String gender;
    private String phoneNumber;
    private String photoURL;
    private double rating;
}
