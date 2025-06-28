package com.example.antitouch.service;


import com.example.antitouch.dto.PcDto;
import com.example.antitouch.entity.Branch;
import com.example.antitouch.entity.Pc;
import com.example.antitouch.mapper.PcMapper;
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
    private final PcMapper mapper;

    public List<PcDto> getAll() {
        return pcRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public PcDto getById(Long id) {
        return mapper.toDto(pcRepository.findById(id).orElseThrow());
    }

    public PcDto create(PcDto dto) {
        Pc pc = mapper.toEntity(dto);
        Branch branch = branchRepository.findById(dto.getBranchId()).orElseThrow();
        pc.setBranch(branch);
        return mapper.toDto(pcRepository.save(pc));
    }

    public PcDto update(Long id, PcDto dto) {
        Pc existing = pcRepository.findById(id).orElseThrow();
        existing.setProcessor(dto.getProcessor());
        existing.setGpu(dto.getGpu());
        existing.setMotherboard(dto.getMotherboard());
        existing.setRam(dto.getRam());
        existing.setDisk(dto.getDisk());
        existing.setGamesInstalled(dto.getGamesInstalled());
        existing.setMonitorHz(dto.getMonitorHz());
        existing.setStatus(dto.getStatus());
        existing.setBranch(branchRepository.findById(dto.getBranchId()).orElseThrow());
        return mapper.toDto(pcRepository.save(existing));
    }

    public void delete(Long id) {
        pcRepository.deleteById(id);
    }
}