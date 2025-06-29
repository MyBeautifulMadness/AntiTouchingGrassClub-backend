package com.example.antitouch.controller;

import com.example.antitouch.dto.PcDto;
import com.example.antitouch.dto.PcUpdateRequest;
import com.example.antitouch.dto.PlaceDto;
import com.example.antitouch.entity.Branch;
import com.example.antitouch.entity.Pc;
import com.example.antitouch.entity.User;
import com.example.antitouch.repository.BranchRepository;
import com.example.antitouch.repository.PcRepository;
import com.example.antitouch.service.AuthService;
import com.example.antitouch.service.PcService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/pcs")
@RequiredArgsConstructor
public class PcAdminController {

    private final PcService pcService;
    private final AuthService authService;
    private final BranchRepository branchRepository;
    private final PcRepository pcRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PcDto create(@RequestHeader("Authorization") String token,
                        @RequestBody PcDto dto) {
        User user = getUserOrThrow(token);
        if (!user.isAdmin()) {
            throw new RuntimeException("Forbidden");
        }

        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Pc pc = new Pc();
        pc.setProcessor(dto.getProcessor());
        pc.setGpu(dto.getGpu());
        pc.setMotherboard(dto.getMotherboard());
        pc.setRam(dto.getRam());
        pc.setDisk(dto.getDisk());
        pc.setGamesInstalled(dto.getGamesInstalled());
        pc.setMonitorHz(dto.getMonitorHz());
        pc.setStatus(dto.getStatus());
        pc.setBranch(branch);
        pc.setX(dto.getX());
        pc.setY(dto.getY());

        pc = pcRepository.save(pc);

        List<PlaceDto> places = new ArrayList<>();
        try {
            if (branch.getPlacesJson() != null) {
                places = new ObjectMapper().readValue(branch.getPlacesJson(),
                        new TypeReference<>() {});
            }
        } catch (Exception ignored) {}

        for (PlaceDto place : places) {
            if (place.getX() == dto.getX() && place.getY() == dto.getY()) {
                place.setHasPc(true);
                place.setPcId(pc.getId());
                break;
            }
        }

        try {
            String updatedJson = new ObjectMapper().writeValueAsString(places);
            branch.setPlacesJson(updatedJson);
            branchRepository.save(branch);
        } catch (Exception ignored) {}

        PcDto response = new PcDto();
        response.setId(pc.getId());
        response.setProcessor(pc.getProcessor());
        response.setGpu(pc.getGpu());
        response.setMotherboard(pc.getMotherboard());
        response.setRam(pc.getRam());
        response.setDisk(pc.getDisk());
        response.setGamesInstalled(pc.getGamesInstalled());
        response.setMonitorHz(pc.getMonitorHz());
        response.setStatus(pc.getStatus());
        response.setBranchId(branch.getId());
        response.setX(pc.getX());
        response.setY(pc.getY());
        return response;
    }

    @PutMapping("/{id}")
    public PcDto updatePc(@RequestHeader("Authorization") String token,
                          @PathVariable Long id,
                          @RequestBody PcUpdateRequest request) {
        User user = getUserOrThrow(token);
        if (!user.isAdmin()) {
            throw new RuntimeException("Forbidden");
        }

        Pc pc = pcRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PC not found"));

        pc.setProcessor(request.getProcessor());
        pc.setGpu(request.getGpu());
        pc.setMotherboard(request.getMotherboard());
        pc.setRam(request.getRam());
        pc.setDisk(request.getDisk());
        pc.setGamesInstalled(request.getGamesInstalled());
        pc.setMonitorHz(request.getMonitorHz());
        pc.setStatus(request.getStatus());

        pc = pcRepository.save(pc);

        PcDto response = new PcDto();
        response.setId(pc.getId());
        response.setProcessor(pc.getProcessor());
        response.setGpu(pc.getGpu());
        response.setMotherboard(pc.getMotherboard());
        response.setRam(pc.getRam());
        response.setDisk(pc.getDisk());
        response.setGamesInstalled(pc.getGamesInstalled());
        response.setMonitorHz(pc.getMonitorHz());
        response.setStatus(pc.getStatus());
        response.setBranchId(pc.getBranch().getId());
        response.setX(pc.getX());
        response.setY(pc.getY());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String token,
                       @PathVariable Long id) {
        User user = getUserOrThrow(token);
        if (!user.isAdmin()) {
            throw new RuntimeException("Forbidden");
        }

        Pc pc = pcRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PC not found"));

        Branch branch = pc.getBranch();
        if (branch != null) {
            List<PlaceDto> places = new ArrayList<>();
            try {
                if (branch.getPlacesJson() != null) {
                    places = new ObjectMapper().readValue(branch.getPlacesJson(), new TypeReference<>() {});
                }
            } catch (Exception ignored) {}

            for (PlaceDto place : places) {
                if (place.getPcId() != null && place.getPcId().equals(pc.getId())) {
                    place.setHasPc(false);
                    place.setPcId(null);
                }
            }

            try {
                String updatedJson = new ObjectMapper().writeValueAsString(places);
                branch.setPlacesJson(updatedJson);
                branchRepository.save(branch);
            } catch (Exception ignored) {}
        }

        pcRepository.delete(pc);
    }

    private User getUserOrThrow(String token) {
        String clean = token.replace("Token ", "").replace("Bearer ", "");
        return authService.getUserByToken(clean)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));
    }
}
