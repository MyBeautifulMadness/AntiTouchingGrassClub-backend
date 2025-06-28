package com.example.antitouch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingTimeDto {
    private LocalDateTime start_date;
    private LocalDateTime end_date;
}
