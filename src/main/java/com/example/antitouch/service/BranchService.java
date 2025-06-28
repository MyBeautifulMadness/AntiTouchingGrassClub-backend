package com.example.antitouch.service;


import com.example.antitouch.dto.BranchDto;
import com.example.antitouch.dto.PlaceDto;
import com.example.antitouch.entity.Branch;
//import com.example.antitouch.mapper.BranchMapper;
import com.example.antitouch.repository.BranchRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        //entity.setLayoutUrl(dto.getLayoutUrl());
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    private BranchDto toDto(Branch branch) {
        List<PlaceDto> places = new ArrayList<>();
        if (branch.getPlacesJson() != null) {
            try {
                places = objectMapper.readValue(branch.getPlacesJson(),
                        new TypeReference<List<PlaceDto>>() {});
            } catch (Exception ignored) {}
        }

        BranchDto dto = new BranchDto();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        //dto.setLayoutUrl(branch.getLayoutUrl());
        dto.setWidth(branch.getWidth());
        dto.setHeight(branch.getHeight());
        dto.setPlaces(places);
        return dto;
    }

    private Branch toEntity(BranchDto dto) {
        Branch entity = new Branch();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        //entity.setLayoutUrl(dto.getLayoutUrl());
        entity.setWidth(dto.getWidth());
        entity.setHeight(dto.getHeight());
        try {
            entity.setPlacesJson(objectMapper.writeValueAsString(dto.getPlaces()));
        } catch (Exception e) {
            entity.setPlacesJson("[]");
        }
        return entity;
    }
}
