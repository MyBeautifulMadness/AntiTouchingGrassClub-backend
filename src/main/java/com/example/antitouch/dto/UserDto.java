package com.example.antitouch.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String phone;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private boolean isAdmin;
}
