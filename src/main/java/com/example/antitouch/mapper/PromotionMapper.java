package com.example.antitouch.mapper;

import com.example.antitouch.dto.PromotionDto;
import com.example.antitouch.entity.Promotion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    PromotionDto toDto(Promotion promotion);
    Promotion toEntity(PromotionDto dto);
}
