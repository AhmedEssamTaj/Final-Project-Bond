package com.example.finalprojectbond.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Explorer {
    @Id
    private Integer id;

    @Column(columnDefinition = "Boolean default false")
    private Boolean meetingZone =false;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "explorer")
    private Set<ReviewExplorer> reviewExplorers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "explorer")
    private Set<Application> applications = new HashSet<>();

    @ManyToMany
    @JsonIgnore
    private Set<Experience> experiences = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "explorer")
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "explorer")
    private Set<Notification> notifications = new HashSet<>();

}
