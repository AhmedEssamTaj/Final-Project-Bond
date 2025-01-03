package com.example.finalprojectbond.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    private String title;

    @Column()
    private LocalDate notification_createAt = LocalDate.now();

    @ManyToOne
    @JsonIgnore
    private Explorer explorer;

    @ManyToOne
    @JsonIgnore
    private Experience experience;

    public Notification(String message, @Size(max = 50, message = "Title must be at most 50 characters") @NotEmpty(message = "Title cannot be empty") String title) {
        this.message = message;
        this.title = title;
    }
}
