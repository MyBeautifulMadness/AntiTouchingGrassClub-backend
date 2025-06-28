package com.example.antitouch.mapper;


import com.example.antitouch.dto.UserDto;
import com.example.antitouch.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto dto);
}
