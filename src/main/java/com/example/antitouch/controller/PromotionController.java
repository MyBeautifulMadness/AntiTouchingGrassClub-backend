package com.example.antitouch.controller;

import com.example.antitouch.dto.PromotionDto;
import com.example.antitouch.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService service;

    @GetMapping
    public List<PromotionDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PromotionDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public PromotionDto create(@RequestBody PromotionDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PromotionDto update(@PathVariable Long id, @RequestBody PromotionDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}