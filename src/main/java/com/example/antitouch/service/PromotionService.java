package com.example.antitouch.service;


import com.example.antitouch.dto.PromotionDto;
import com.example.antitouch.entity.Promotion;
import com.example.antitouch.mapper.PromotionMapper;
import com.example.antitouch.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository repository;
    private final PromotionMapper mapper;

    public List<PromotionDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public PromotionDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    public PromotionDto create(PromotionDto dto) {
        Promotion entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public PromotionDto update(Long id, PromotionDto dto) {
        Promotion existing = repository.findById(id).orElseThrow();
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setImageUrl(dto.getImageUrl());
        existing.setType(dto.getType());
        existing.setPlatform(dto.getPlatform());
        existing.setActive(dto.isActive());
        return mapper.toDto(repository.save(existing));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
