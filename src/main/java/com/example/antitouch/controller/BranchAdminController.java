package com.example.antitouch.controller;

import com.example.antitouch.dto.BranchDto;
import com.example.antitouch.dto.PlaceDto;
import com.example.antitouch.entity.Branch;
import com.example.antitouch.entity.User;
import com.example.antitouch.repository.BranchRepository;
import com.example.antitouch.service.AuthService;
import com.example.antitouch.service.BranchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/branches")
@RequiredArgsConstructor
public class BranchAdminController {

    private final BranchService branchService;
    private final AuthService authService;
    private final BranchRepository branchRepository;
    /*
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
    */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BranchDto createBranch(@RequestHeader("Authorization") String token,
                                  @RequestBody BranchDto dto) {
        User user = getUserOrThrow(token);
        if (!user.isAdmin()) {
            throw new RuntimeException("Forbidden");
        }

        List<PlaceDto> generatedPlaces = new ArrayList<>();
        for (int x = 0; x < dto.getWidth(); x++) {
            for (int y = 0; y < dto.getHeight(); y++) {
                PlaceDto place = new PlaceDto();
                place.setX(x);
                place.setY(y);
                place.setHasPc(false);
                place.setPcId(null);
                generatedPlaces.add(place);
            }
        }

        String placesJson = "[]";
        try {
            placesJson = new ObjectMapper().writeValueAsString(generatedPlaces);
        } catch (Exception ignored) {}

        Branch branch = new Branch();
        branch.setName(dto.getName());
        branch.setWidth(dto.getWidth());
        branch.setHeight(dto.getHeight());
        branch.setPlacesJson(placesJson);

        Branch saved = branchRepository.save(branch);

        BranchDto response = new BranchDto();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setWidth(saved.getWidth());
        response.setHeight(saved.getHeight());
        response.setPlaces(generatedPlaces);
        return response;
    }


    private User getUserOrThrow(String token) {
        String clean = token.replace("Token ", "").replace("Bearer ", "");
        return authService.getUserByToken(clean)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));
    }
}
