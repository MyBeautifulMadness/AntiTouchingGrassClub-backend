package com.example.antitouch.controller;

import com.example.antitouch.dto.BranchDto;
import com.example.antitouch.entity.Branch;
import com.example.antitouch.entity.User;
import com.example.antitouch.repository.BranchRepository;
import com.example.antitouch.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchRepository branchRepository;
    private final AuthService authService;

    @GetMapping
    public List<BranchDto> getAllBranches(@RequestHeader("Authorization") String token) {
        getUserOrThrow(token); // Авторизация
        return branchRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    private BranchDto toDto(Branch branch) {
        BranchDto dto = new BranchDto();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setLayoutUrl(branch.getLayoutUrl());
        return dto;
    }

    private User getUserOrThrow(String tokenHeader) {
        String token = tokenHeader.replace("Token ", "").replace("Bearer ", "");
        return authService.getUserByToken(token)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));
    }
}
