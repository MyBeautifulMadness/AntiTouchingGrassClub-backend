package com.example.antitouch.dto;


import com.example.antitouch.entity.PcStatus;
import lombok.Data;

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
}
