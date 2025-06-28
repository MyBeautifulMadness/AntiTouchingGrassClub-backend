package com.example.antitouch.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateProfileRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String phone;
}
