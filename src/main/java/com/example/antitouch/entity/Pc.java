package com.example.antitouch.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pcs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String processor;
    private String gpu;
    private String motherboard;
    private String ram;
    private String disk;
    private String gamesInstalled;
    private Integer monitorHz;

    @Enumerated(EnumType.STRING)
    private PcStatus status;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    private Integer x;
    private Integer y;
}
