package com.example.antitouch.service;

import com.example.antitouch.dto.UserDto;
import com.example.antitouch.entity.User;
import com.example.antitouch.mapper.UserMapper;
import com.example.antitouch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public UserDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    public UserDto create(UserDto dto) {
        User entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
