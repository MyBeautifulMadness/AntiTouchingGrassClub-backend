package com.example.antitouch.controller;

import com.example.antitouch.entity.User;
import com.example.antitouch.repository.UserRepository;
import com.example.antitouch.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/admins")
@RequiredArgsConstructor
public class AdminRoleController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makeAdmin(@RequestHeader("Authorization") String token,
                          @RequestParam Long userId) {
        checkAdmin(token);

        User user = userRepository.findById(userId).orElseThrow();
        user.setAdmin(true);
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAdmin(@RequestHeader("Authorization") String token,
                            @PathVariable Long id) {
        checkAdmin(token);

        User user = userRepository.findById(id).orElseThrow();
        user.setAdmin(false);
        userRepository.save(user);
    }

    private void checkAdmin(String token) {
        String clean = token.replace("Token ", "").replace("Bearer ", "");
        User user = authService.getUserByToken(clean)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));

        if (!user.isAdmin()) {
            throw new RuntimeException("Forbidden");
        }
    }
}
