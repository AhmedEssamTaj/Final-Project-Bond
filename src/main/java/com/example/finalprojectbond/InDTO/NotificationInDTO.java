package com.example.finalprojectbond.InDTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class NotificationInDTO {
    private String message;

    private String title;
}
