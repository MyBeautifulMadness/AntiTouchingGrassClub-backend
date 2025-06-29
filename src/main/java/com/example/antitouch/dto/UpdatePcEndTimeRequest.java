package com.example.antitouch.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdatePcEndTimeRequest {
    private LocalDateTime endTime;
}