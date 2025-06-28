package com.example.antitouch.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String phone;
    private String username;
    private String password;
}
