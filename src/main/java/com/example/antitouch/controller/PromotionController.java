package com.example.antitouch.controller;

import com.example.antitouch.dto.PromotionCreateRequest;
import com.example.antitouch.entity.Promotion;
import com.example.antitouch.repository.PromotionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionRepository repository;

    public PromotionController(PromotionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Promotion> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Promotion getById(@PathVariable String id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Promotion create(@RequestBody PromotionCreateRequest request) {
        Promotion promotion = new Promotion();
        promotion.setType(request.getType());
        promotion.setPromotionValue(request.getPromotionValue());
        promotion.setDescription(request.getDescription());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion.setPlatformFor(request.getPlatformFor());
        promotion.setImageId(request.getImageId());
        return repository.save(promotion);
    }

    @PutMapping("/{id}")
    public Promotion update(@PathVariable String id, @RequestBody PromotionCreateRequest updated) {
        Promotion promotion = repository.findById(id).orElseThrow();
        promotion.setType(updated.getType());
        promotion.setPromotionValue(updated.getPromotionValue());
        promotion.setDescription(updated.getDescription());
        promotion.setStartDate(updated.getStartDate());
        promotion.setEndDate(updated.getEndDate());
        promotion.setPlatformFor(updated.getPlatformFor());
        promotion.setImageId(updated.getImageId());
        return repository.save(promotion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}