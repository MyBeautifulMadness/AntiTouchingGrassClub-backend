package com.example.antitouch.mapper;


import com.example.antitouch.dto.BookingDto;
import com.example.antitouch.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDto toDto(Booking booking);
    Booking toEntity(BookingDto dto);
}
