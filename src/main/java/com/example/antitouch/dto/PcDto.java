package com.example.antitouch.dto;


import com.example.antitouch.entity.PcStatus;
import lombok.*;

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
}
