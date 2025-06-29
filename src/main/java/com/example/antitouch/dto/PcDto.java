package com.example.antitouch.dto;


import com.example.antitouch.entity.PcStatus;
import com.example.antitouch.entity.PriceLevel;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PcDto {
    private Long id;
    private String processor;
    private String gpu;
    private String motherboard;
    private String ram;
    private String disk;
    private String gamesInstalled;
    private Integer monitorHz;
    private PcStatus status;
    private Long branchId;
    private Integer x;
    private Integer y;

    private PriceLevel priceLevel;
    private LocalDateTime endTime;
}
