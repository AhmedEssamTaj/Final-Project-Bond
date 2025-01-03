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

    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @Column(columnDefinition = "varchar(30) not null")
    private String city;

    @NotEmpty(message = "Status cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    @Pattern(regexp = "^(Accept Application|Fully Booked|Confirming|Task Assignment|In Progress|Active|Completed|Canceled)$")
    private String status = "Accept Application";

    @Column(columnDefinition = "date not null")
    private LocalDate startDate;

    @Column(columnDefinition = "date not null")
    private LocalDate endDate;

    @Column(columnDefinition = "date DEFAULT CURRENT_DATE")
    private LocalDate createdAt = LocalDate.now();

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
