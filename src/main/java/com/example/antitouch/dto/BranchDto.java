package com.example.antitouch.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDto {
    private Long id;
    private String name;
    //private String layoutUrl;
    private int width;
    private int height;
    private List<PlaceDto> places;
}
