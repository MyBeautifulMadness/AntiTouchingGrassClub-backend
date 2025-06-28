package com.example.antitouch.dto;

import com.example.antitouch.entity.PlatformType;
import com.example.antitouch.entity.PromotionType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionDto {
    private Long id;
    private String title;
    private PromotionType type;
    private PlatformType platform;
    private String description;
    private String imageUrl;
    private boolean isActive;
}
