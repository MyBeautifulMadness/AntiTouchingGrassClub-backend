package com.example.antitouch.dto;

import com.example.antitouch.enums.PlatformType;
import com.example.antitouch.enums.PromotionType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PromotionCreateRequest {
    private PromotionType type;
    private double promotionValue;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private PlatformType platformFor;
    private String imageId;
}