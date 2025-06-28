package com.example.antitouch.controller;

import com.example.antitouch.dto.PcDto;
import com.example.antitouch.entity.User;
import com.example.antitouch.service.AuthService;
import com.example.antitouch.service.PcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/pcs")
@RequiredArgsConstructor
public class PcAdminController {

    private final PcService pcService;
    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PcDto create(@RequestHeader("Authorization") String token,
                        @RequestBody PcDto dto) {
        User user = getUserOrThrow(token);
        if (!user.isAdmin()) {
            throw new RuntimeException("Forbidden");
        }

        return pcService.create(dto);
    }

    @PutMapping("/{id}")
    public PcDto update(@RequestHeader("Authorization") String token,
                        @PathVariable Long id,
                        @RequestBody PcDto dto) {
        User user = getUserOrThrow(token);
        if (!user.isAdmin()) {
            throw new RuntimeException("Forbidden");
        }

        return pcService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String token,
                       @PathVariable Long id) {
        User user = getUserOrThrow(token);
        if (!user.isAdmin()) {
            throw new RuntimeException("Forbidden");
        }

        pcService.delete(id);
    }

    private User getUserOrThrow(String token) {
        String clean = token.replace("Token ", "").replace("Bearer ", "");
        return authService.getUserByToken(clean)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));
    }
}
