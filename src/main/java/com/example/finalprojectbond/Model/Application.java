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
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255,message = "The length of the description must be at most 255 characters")
    @NotEmpty(message = "Description cannot be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @Size(max = 120,message = "The length of the tools must be at most 120 characters")
    @NotEmpty(message = "Tools cannot be null")
    @Column(columnDefinition = "varchar(120) not null")
    private String tools;

    @Size(max = 10,message = "The length of the status must be at most 10 characters")
    @Column(columnDefinition = "varchar(20)") // the default is in-complete
    @Pattern(regexp = "^(Accepted|In-Progress|Rejected)$",message = "The status must be Accepted, In-Progress or Rejected")
    private String status;

    @ManyToOne
    @JsonIgnore
    private Explorer explorer;

    @ManyToOne
    @JsonIgnore
    private Experience experience;
}
