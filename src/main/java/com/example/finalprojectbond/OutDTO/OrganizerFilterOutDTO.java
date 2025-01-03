package com.example.finalprojectbond.OutDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class OrganizerFilterOutDTO {
    private String photoURL;

    private String username;

    private String name;

    private String city;

    private String userProfileSummary;

    private Integer rating;
}
