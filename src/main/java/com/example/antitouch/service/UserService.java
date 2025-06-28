package com.example.antitouch.service;

import com.example.antitouch.dto.UserDto;
import com.example.antitouch.entity.User;
import com.example.antitouch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserDto> getAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public UserDto getById(Long id) {
        User user = repository.findById(id).orElseThrow();
        return toDto(user);
    }

    public UserDto create(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setUsername(dto.getUsername());
        user.setAdmin(dto.isAdmin());
        return toDto(repository.save(user));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setUsername(user.getUsername());
        dto.setAdmin(user.isAdmin());
        return dto;
    }
}
