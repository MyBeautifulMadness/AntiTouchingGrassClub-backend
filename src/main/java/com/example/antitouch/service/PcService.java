package com.example.antitouch.service;


import com.example.antitouch.dto.PcDto;
import com.example.antitouch.entity.Branch;
import com.example.antitouch.entity.Pc;
//import com.example.antitouch.mapper.PcMapper;
import com.example.antitouch.repository.BranchRepository;
import com.example.antitouch.repository.PcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PcService {

    private final PcRepository pcRepository;
    private final BranchRepository branchRepository;
    //private final PcMapper mapper;

    public List<PcDto> getByBranch(Long branchId) {
        return pcRepository.findAll().stream()
                .filter(pc -> pc.getBranch() != null && pc.getBranch().getId().equals(branchId))
                .map(this::toDto)
                .toList();
    }

    /*
    public PcDto getById(Long id) {
        return mapper.toDto(pcRepository.findById(id).orElseThrow());
    }

     */

    public PcDto create(PcDto dto) {
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

        return toDto(pcRepository.save(pc));
    }

    public PcDto update(Long id, PcDto dto) {
        Pc pc = pcRepository.findById(id).orElseThrow();
        Branch branch = branchRepository.findById(dto.getBranchId()).orElseThrow();

        pc.setProcessor(dto.getProcessor());
        pc.setGpu(dto.getGpu());
        pc.setMotherboard(dto.getMotherboard());
        pc.setRam(dto.getRam());
        pc.setDisk(dto.getDisk());
        pc.setGamesInstalled(dto.getGamesInstalled());
        pc.setMonitorHz(dto.getMonitorHz());
        pc.setStatus(dto.getStatus());
        pc.setBranch(branch);

        return toDto(pcRepository.save(pc));
    }

    public void delete(Long id) {
        pcRepository.deleteById(id);
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
        return dto;
    }
}