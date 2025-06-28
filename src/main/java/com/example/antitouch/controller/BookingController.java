package com.example.antitouch.controller;

import com.example.antitouch.dto.BookingDto;
import com.example.antitouch.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @GetMapping
    public List<BookingDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BookingDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public BookingDto create(@RequestBody BookingDto dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
