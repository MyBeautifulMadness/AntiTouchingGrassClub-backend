package com.example.antitouch.mapper;

import com.example.antitouch.dto.BranchDto;
import com.example.antitouch.entity.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchDto toDto(Branch branch);
    Branch toEntity(BranchDto dto);
}
