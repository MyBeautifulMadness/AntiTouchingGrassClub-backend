package com.example.demo.promotions.dto;

import com.example.demo.promotions.enums.PlatformType;
import com.example.demo.promotions.enums.PromotionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PromotionCreateRequest {
    private PromotionType type;
    private double promotionValue;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PlatformType platformFor;
    private String imageId;
}