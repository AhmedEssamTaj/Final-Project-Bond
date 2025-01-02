package com.example.finalprojectbond.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20,message = "The length of the title must be at most 20 characters")
    @NotEmpty(message = "Title cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;

    @Size(max = 255,message = "The length of the description must be at most 255 characters")
    @NotEmpty(message = "Description cannot be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @Size(max = 11,message = "The length of the status must be at most 11 characters")
    @Column(columnDefinition = "varchar(11)")
    @Pattern(regexp = "^(Complete|In-Complete)$",message = "The status must be Complete or In-Complete")
    private String status = "In-Complete";

    @ManyToOne
    @JsonIgnore
    private Explorer explorer;

    @ManyToOne
    @JsonIgnore
    private Experience experience;
}
