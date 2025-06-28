package com.example.antitouch.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "promotions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private PromotionType type;

    @Enumerated(EnumType.STRING)
    private PlatformType platform;

    private String description;

    private String imageUrl;

    private boolean isActive;
}
