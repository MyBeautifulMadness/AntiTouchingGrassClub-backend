package com.example.antitouch.service;


import com.example.antitouch.dto.BookingDto;
import com.example.antitouch.entity.Booking;
import com.example.antitouch.mapper.BookingMapper;
import com.example.antitouch.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository repository;
    private final BookingMapper mapper;

    public List<BookingDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public BookingDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    public BookingDto create(BookingDto dto) {
        Booking entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
