package com.example.finalprojectbond.InDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class MeetingZoneInDTO {
    private Integer experienceId;

    @Positive(message = "Latitude cannot be null")
    private Double latitude;

    @Positive(message = "longitude cannot be null")
    private Double longitude;

    @Size(max = 255, message = "The length of the landmark must be at most 255 characters")
    @NotEmpty(message = "Land Mark cannot be empty")
    private String landMark;
}
