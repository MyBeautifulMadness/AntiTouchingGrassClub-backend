package com.example.antitouch.controller;

import com.example.antitouch.dto.UserDto;
import com.example.antitouch.entity.User;
import com.example.antitouch.repository.UserRepository;
import com.example.antitouch.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @GetMapping
    public List<UserDto> getAll(@RequestHeader("Authorization") String token) {
        checkAdmin(token);
        return userRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getById(@RequestHeader("Authorization") String token,
                           @PathVariable Long id) {
        checkAdmin(token);
        User user = userRepository.findById(id).orElseThrow();
        return toDto(user);
    }

    private void checkAdmin(String token) {
        String clean = token.replace("Token ", "").replace("Bearer ", "");
        User user = authService.getUserByToken(clean)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));

        if (!user.isAdmin()) {
            throw new RuntimeException("Forbidden");
        }
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
