package com.example.antitouch.controller;


import com.example.antitouch.dto.*;
import com.example.antitouch.entity.User;
import com.example.antitouch.repository.UserRepository;
import com.example.antitouch.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @GetMapping("/profile")
    public UserDto me(@RequestHeader("Authorization") String token) {
        String cleanToken = token.replace("Token ", "").replace("Bearer ", "");
        User user = authService.getUserByToken(cleanToken)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));

        return toDto(user);
    }

    @PutMapping("/profile")
    public UserDto updateProfile(@RequestHeader("Authorization") String token,
                                 @RequestBody UpdateProfileRequest request) {
        String cleanToken = token.replace("Token ", "").replace("Bearer ", "");
        User user = authService.getUserByToken(cleanToken)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthday(request.getBirthday());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        userRepository.save(user);

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
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthday(user.getBirthday())
                .isAdmin(user.isAdmin())
                .build();
    }
}
