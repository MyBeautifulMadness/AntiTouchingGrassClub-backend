package com.example.antitouch.service;


import com.example.antitouch.dto.AuthRequest;
import com.example.antitouch.dto.AuthResponse;
import com.example.antitouch.dto.RegisterRequest;
import com.example.antitouch.entity.User;
import com.example.antitouch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final Map<String, User> tokenStorage = new HashMap<>();

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setAdmin(false);

        userRepository.save(user);
        String token = generateToken();
        tokenStorage.put(token, user);
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .filter(u -> Objects.equals(u.getPassword(), request.getPassword()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = generateToken();
        tokenStorage.put(token, user);
        return new AuthResponse(token);
    }

    public Optional<User> getUserByToken(String token) {
        return Optional.ofNullable(tokenStorage.get(token));
    }

    private String generateToken() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public void logout(String token) {
        System.out.println("Before logout: " + tokenStorage.keySet());
        tokenStorage.remove(token);
        System.out.println("After logout: " + tokenStorage.keySet());
    }
}
