package com.example.finalprojectbond.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Organizer {
    @Id
    private Integer id;

    @Size(max = 255,message = "The length of the user profile summary must be at most 255 characters")
    @NotEmpty(message = "User Profile Summary cannot be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String userProfileSummary;

    @Column(columnDefinition = "int default 0")
    private Integer numberOfExperience = 0;

    @Size(max = 20,message = "The length of the license serial number must be at most 20 characters")
    @NotEmpty(message = "license serial number cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String licenseSerialNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizer")
    private Set<ReviewExplorer> reviewExplorers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizer")
    private Set<Experience> experiences = new HashSet<>();
}
