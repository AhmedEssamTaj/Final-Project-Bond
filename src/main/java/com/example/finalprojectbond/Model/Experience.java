package com.example.finalprojectbond.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Size(max = 50, message = "Title must be at most 50 characters")
    @NotEmpty(message = "Title cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    @Size(max = 500, message = "Description must be at most 500 characters")
    @NotEmpty(message = "Description cannot be empty")
    @Column(columnDefinition = "varchar(500) not null")
    private String description;

    @Size(max = 30, message = "City must not exceed 30 characters")
    @NotEmpty(message = "City cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String city;

    @Size(max = 20, message = "Status must be at most 20 characters")
    @NotEmpty(message = "Status cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "^(Accept Application|Fully Booked|Confirming|Task Assignment|In Progress|Active|Completed|Canceled)$")
    private String status;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be today or in the future")
    @Column(columnDefinition = "date not null")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @Future(message = "End date must be in the future")
    @Column(columnDefinition = "date not null")
    private LocalDate endDate;

    @Column(columnDefinition = "date DEFAULT CURRENT_DATE")
    private LocalDate createdAt = LocalDate.now();

    @Size(max = 15, message = "Difficulty must not exceed 15 characters")
    @NotEmpty(message = "Difficulty cannot be empty")
    @Column(columnDefinition = "varchar(15) not null")
    private String difficulty;

    @Column(columnDefinition = "varchar(6) not null")
    @Pattern(regexp = "^(MALE|FEMALE|FAMILY)$")
    private String audienceType;

    @ManyToOne
    @JsonIgnore
    private Organizer organizer;

    @ManyToMany(mappedBy = "experiences")
    private Set<Explorer> explorers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experience")
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experience")
    private Set<ExperiencePhoto> experiencePhotos = new HashSet<>();

    @ManyToMany(mappedBy = "experiences")
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experience")
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experience")
    private Set<Application> applications = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MeetingZone meetingZone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experience")
    private Set<ReviewExperience> reviewExperiences = new HashSet<>();
}
