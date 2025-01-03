package com.example.finalprojectbond.Model;

import com.example.finalprojectbond.Service.MyUserDetailsService;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Size(max = 25,message = "The length of the user name must be at most 25 characters")
//    @NotEmpty(message = "User Name cannot be null")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;

    @Column(columnDefinition = "varchar(50) not null")
    private String name;
//    @Size(max = 25,message = "The length of the password must be at most 25 characters")
//    @NotEmpty(message = "Password cannot be null")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String password;

//    @Positive(message = "Age cannot be null ")
//    @Min(value = 18, message = "Age must be at least 18 ")
//    @Max(value = 100, message = "Age cannot be more than 100 ")
    @Column(columnDefinition = "int not null")
    private Integer age;

//    @Size(max = 30,message = "The length of the city must be at most 30 characters")
//    @NotEmpty(message = "City cannot be null")
    @Column(columnDefinition = "varchar(30) not null")
    private String city;

//    @Size(max = 70,message = "The length of the health status must be at most 70 characters")
//    @NotEmpty(message = "health status cannot be null")
    @Column(columnDefinition = "varchar(70) not null")
    private String healthStatus;
//
//    @Size(max = 40,message = "The length of the city must be at most 40 characters")
//    @NotEmpty(message = "City cannot be null")
//    @Email(message = "Invalid email format")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;

//    @NotEmpty(message = "Gender cannot be empty")
//    @Pattern(regexp = "^(Male|Female)$", message = "Gender must be either Male or Female")
    @Column(columnDefinition = "varchar(6) not null")
    private String gender;

//    @Size(max = 15, message = "Role must be at most 15 characters")
    @Column
    private String role;

//    @NotEmpty(message = "Phone number cannot be null")
//    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone number must start with 05 and be 10 digits")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;


    @Column()
    private String photoURL;

    @Column(columnDefinition = "double")
    private double rating;



    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Explorer explorer;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Organizer organizer;

}
