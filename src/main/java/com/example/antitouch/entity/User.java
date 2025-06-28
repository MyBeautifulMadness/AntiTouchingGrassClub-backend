package com.example.antitouch.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String phone;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Column(columnDefinition = "DATE")
    private LocalDate birthday;

    private boolean isAdmin;
}