package com.example.finalprojectbond.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String notification_ToUser;

    @Column()
    private LocalDate notification_createAt = LocalDate.now();

    @ManyToOne
    @JsonIgnore
    private Explorer explorer;

    @ManyToOne
    @JsonIgnore
    private Experience experience;
}
