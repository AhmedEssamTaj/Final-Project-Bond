package com.example.finalprojectbond.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
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
public class MeetingZone {
    @Id
    private Integer id;

    @Positive(message = "Latitude cannot be null")
    @Column(columnDefinition = "Double not null")
    private Double latitude;

    @Positive(message = "longitude cannot be null")
    @Column(columnDefinition = "Double not null")
    private Double longitude;

    @Size(max = 255, message = "The length of the landmark must be at most 255 characters")
    @NotEmpty(message = "Land Mark cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String landMark;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Experience experience;

    public MeetingZone(@Positive(message = "Latitude cannot be null") Double latitude, @Positive(message = "longitude cannot be null") Double longitude, @Size(max = 255, message = "The length of the landmark must be at most 255 characters") @NotEmpty(message = "Land Mark cannot be empty") String landMark) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.landMark = landMark;
    }
}
