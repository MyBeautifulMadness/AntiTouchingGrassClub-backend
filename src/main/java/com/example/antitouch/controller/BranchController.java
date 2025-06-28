package com.example.antitouch.controller;

import com.example.antitouch.dto.BranchDto;
import com.example.antitouch.dto.PlaceDto;
import com.example.antitouch.entity.Branch;
import com.example.antitouch.entity.User;
import com.example.antitouch.repository.BranchRepository;
import com.example.antitouch.service.AuthService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchRepository branchRepository;
    private final AuthService authService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public List<BranchDto> getAllBranches(@RequestHeader("Authorization") String token) {
        getUserOrThrow(token);
        return branchRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    private BranchDto toDto(Branch branch) {
        List<PlaceDto> places = new ArrayList<>();
        try {
            if (branch.getPlacesJson() != null) {
                places = objectMapper.readValue(branch.getPlacesJson(), new TypeReference<>() {});
            }
        } catch (Exception e) {
            //
        }

        BranchDto dto = new BranchDto();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setWidth(branch.getWidth());
        dto.setHeight(branch.getHeight());
        dto.setPlaces(places);
        return dto;
    }

    private User getUserOrThrow(String tokenHeader) {
        String token = tokenHeader.replace("Token ", "").replace("Bearer ", "");
        return authService.getUserByToken(token)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));
    }
}
