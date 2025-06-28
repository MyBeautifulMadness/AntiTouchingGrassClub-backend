package com.example.antitouch.controller;

import com.example.antitouch.dto.BranchDto;
import com.example.antitouch.entity.User;
import com.example.antitouch.service.AuthService;
import com.example.antitouch.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/branches")
@RequiredArgsConstructor
public class BranchAdminController {

    private final BranchService branchService;
    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BranchDto create(@RequestHeader("Authorization") String token,
                            @RequestBody BranchDto dto) {
        User user = getUserOrThrow(token);
        if (!user.isAdmin()) {
            throw new RuntimeException("Forbidden");
        }

        return branchService.create(dto);
    }

    private User getUserOrThrow(String token) {
        String clean = token.replace("Token ", "").replace("Bearer ", "");
        return authService.getUserByToken(clean)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));
    }
}
