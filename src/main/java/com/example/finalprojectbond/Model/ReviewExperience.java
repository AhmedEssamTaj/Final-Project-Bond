package com.example.finalprojectbond.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255,message = "The length of the comment must be at most 255 characters")
    @NotEmpty(message = "Comment cannot be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String comment;

    @Positive(message = "Rating cannot be null ")
    @Min(value = 1, message = "Rating must be at least 1 ")
    @Max(value = 5, message = "Rating cannot be more than 5 ")
    @Column(columnDefinition = "int not null")
    private Integer rating;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JsonIgnore
    private Experience experience;

}
