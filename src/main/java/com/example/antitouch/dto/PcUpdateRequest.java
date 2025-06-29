package com.example.antitouch.dto;


import com.example.antitouch.entity.PcStatus;
import com.example.antitouch.entity.PriceLevel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PcUpdateRequest {
    private String processor;
    private String gpu;
    private String motherboard;
    private String ram;
    private String disk;
    private String gamesInstalled;
    private int monitorHz;
    private PcStatus status;

    private PriceLevel priceLevel;
    private LocalDateTime endTime;
    private Integer x;
    private Integer y;
}
