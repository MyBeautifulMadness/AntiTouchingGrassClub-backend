package com.example.antitouch.dto;

import com.example.antitouch.enums.PlatformType;
import com.example.antitouch.enums.PromotionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PromotionResponse {
    private String id;
    private PromotionType type;
    private double promotionValue;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PlatformType platformFor;
    private String imageId;
}
