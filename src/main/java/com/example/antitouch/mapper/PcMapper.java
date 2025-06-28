package com.example.antitouch.mapper;


import com.example.antitouch.dto.PcDto;
import com.example.antitouch.entity.Pc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PcMapper {
    PcDto toDto(Pc pc);
    Pc toEntity(PcDto dto);
}