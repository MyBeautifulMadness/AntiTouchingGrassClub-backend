package com.example.antitouch.controller;


import com.example.antitouch.dto.AuthRequest;
import com.example.antitouch.dto.AuthResponse;
import com.example.antitouch.dto.RegisterRequest;
import com.example.antitouch.dto.UserDto;
import com.example.antitouch.entity.User;
import com.example.antitouch.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public UserDto me(@RequestHeader("Authorization") String token) {
        String cleanToken = token.replace("Token ", "").replace("Bearer ", "");
        User user = authService.getUserByToken(cleanToken)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));

        return toDto(user);
    }

    @DeleteMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
        String cleanToken = token.replace("Token ", "").replace("Bearer ", "");
        authService.logout(cleanToken);
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .username(user.getUsername())
                .isAdmin(user.isAdmin())
                .build();
    }
}
