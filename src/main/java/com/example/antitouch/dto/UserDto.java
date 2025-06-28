package com.example.antitouch.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String phone;
    private String username;
    private boolean isAdmin;
}
