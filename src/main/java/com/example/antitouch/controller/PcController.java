package com.example.antitouch.controller;

import com.example.antitouch.dto.PcDto;
import com.example.antitouch.entity.Pc;
import com.example.antitouch.entity.User;
import com.example.antitouch.repository.PcRepository;
import com.example.antitouch.service.AuthService;
import com.example.antitouch.service.PcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches/{branchId}/pcs")
@RequiredArgsConstructor
public class PcController {

    private final PcService pcService;
    private final PcRepository pcRepository;
    private final AuthService authService;

    @GetMapping
    public List<PcDto> getByBranch(@RequestHeader("Authorization") String token,
                                   @PathVariable Long branchId) {
        getUserOrThrow(token);
        return pcRepository.findAll().stream()
                .filter(pc -> pc.getBranch() != null && pc.getBranch().getId().equals(branchId))
                .map(pc -> {
                    PcDto dto = new PcDto();
                    dto.setId(pc.getId());
                    dto.setProcessor(pc.getProcessor());
                    dto.setGpu(pc.getGpu());
                    dto.setMotherboard(pc.getMotherboard());
                    dto.setRam(pc.getRam());
                    dto.setDisk(pc.getDisk());
                    dto.setGamesInstalled(pc.getGamesInstalled());
                    dto.setMonitorHz(pc.getMonitorHz());
                    dto.setStatus(pc.getStatus());
                    dto.setBranchId(pc.getBranch().getId());
                    dto.setX(pc.getX());
                    dto.setY(pc.getY());
                    return dto;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public PcDto getById(@RequestHeader("Authorization") String token,
                         @PathVariable Long id) {
        getUserOrThrow(token);
        Pc pc = pcRepository.findById(id).orElseThrow();
        return toDto(pc);
    }

    private PcDto toDto(Pc pc) {
        PcDto dto = new PcDto();
        dto.setId(pc.getId());
        dto.setProcessor(pc.getProcessor());
        dto.setGpu(pc.getGpu());
        dto.setMotherboard(pc.getMotherboard());
        dto.setRam(pc.getRam());
        dto.setDisk(pc.getDisk());
        dto.setGamesInstalled(pc.getGamesInstalled());
        dto.setMonitorHz(pc.getMonitorHz());
        dto.setStatus(pc.getStatus());
        dto.setBranchId(pc.getBranch().getId());
        dto.setX(pc.getX());
        dto.setY(pc.getY());
        return dto;
    }

    private User getUserOrThrow(String tokenHeader) {
        String token = tokenHeader.replace("Token ", "").replace("Bearer ", "");
        return authService.getUserByToken(token)
                .orElseThrow(() -> new RuntimeException("Unauthorized"));
    }
}
