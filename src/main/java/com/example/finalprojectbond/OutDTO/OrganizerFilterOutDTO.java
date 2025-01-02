package com.example.finalprojectbond.OutDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class OrganizerFilterOutDTO {
    private String photoURL;

    private String username;

    private String city;

    private String userProfileSummary;

    private Integer rating;
}
