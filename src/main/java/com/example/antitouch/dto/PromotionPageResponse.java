package com.example.antitouch.dto;

import com.example.antitouch.entity.Promotion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PromotionPageResponse {
    private List<Promotion> items;
    private int currentPage;
    private int pageSize;
    private long totalItems;
    private int totalPages;
}
