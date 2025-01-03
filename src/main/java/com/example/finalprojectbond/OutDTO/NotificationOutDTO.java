package com.example.finalprojectbond.OutDTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class NotificationOutDTO {

    private String message;

    private String notification_ToUser;

    private LocalDate notification_createAt;
}
