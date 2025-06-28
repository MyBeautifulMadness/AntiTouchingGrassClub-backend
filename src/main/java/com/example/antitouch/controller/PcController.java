package com.example.antitouch.controller;

import com.example.antitouch.dto.PcDto;
import com.example.antitouch.service.PcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pcs")
@RequiredArgsConstructor
public class PcController {

    private final PcService service;

    @GetMapping
    public List<PcDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PcDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public PcDto create(@RequestBody PcDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PcDto update(@PathVariable Long id, @RequestBody PcDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
