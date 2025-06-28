package com.example.antitouch.service;


import com.example.antitouch.dto.BranchDto;
import com.example.antitouch.entity.Branch;
//import com.example.antitouch.mapper.BranchMapper;
import com.example.antitouch.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository repository;
    //private final BranchMapper mapper;

    /*
    public List<BranchDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public BranchDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }
    */

    public BranchDto create(BranchDto dto) {
        Branch entity = new Branch();
        entity.setName(dto.getName());
        entity.setLayoutUrl(dto.getLayoutUrl());
        return toDto(repository.save(entity));
    }

    /*
    public BranchDto update(Long id, BranchDto dto) {
        Branch existing = repository.findById(id).orElseThrow();
        existing.setName(dto.getName());
        existing.setLayoutUrl(dto.getLayoutUrl());
        return mapper.toDto(repository.save(existing));
    }
    */

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private BranchDto toDto(Branch branch) {
        BranchDto dto = new BranchDto();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setLayoutUrl(branch.getLayoutUrl());
        return dto;
    }
}
