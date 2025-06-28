package com.example.antitouch.controller;

import com.example.antitouch.dto.BranchDto;
import com.example.antitouch.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService service;

    @GetMapping
    public List<BranchDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BranchDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public BranchDto create(@RequestBody BranchDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public BranchDto update(@PathVariable Long id, @RequestBody BranchDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
