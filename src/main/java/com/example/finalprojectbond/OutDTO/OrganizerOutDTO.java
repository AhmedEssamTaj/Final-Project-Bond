package com.example.finalprojectbond.OutDTO;

import com.example.finalprojectbond.Model.Experience;
import com.example.finalprojectbond.Model.MyUser;
import com.example.finalprojectbond.Model.ReviewExplorer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
public class OrganizerOutDTO {
    private String username;

    private String name;

    private Integer age;

    private String city;

    private String healthStatus;

    private String email;

    private String gender;

    private String role;

    private String phoneNumber;

    private String photoURL;

    private String userProfileSummary;

    private Integer numberOfExperience;

    private String licenseSerialNumber;

    private double rating;

    private Boolean isApproved;

}
