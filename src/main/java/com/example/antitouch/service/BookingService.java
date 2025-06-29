package com.example.antitouch.service;

import com.example.antitouch.FileUploader;
import com.example.antitouch.QrCodeGenerator;
import com.example.antitouch.dto.BookingRequest;
import com.example.antitouch.dto.BookingResponse;
import com.example.antitouch.dto.BookingTimeDto;
import com.example.antitouch.entity.Booking;
import com.example.antitouch.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingResponse createBooking(BookingRequest request) {
        Booking booking = Booking.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .pcId(request.getPcId())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .paymentMethod(request.getPaymentMethod())
                .finalPrice(request.getFinalPrice())
                .build();

        booking = bookingRepository.save(booking);

        try {
            BufferedImage confirmQr = QrCodeGenerator.generateQrImage("CONFIRM#" + booking.getId(), 300);
            BufferedImage paymentQr = QrCodeGenerator.generateQrImage("PAY#" + booking.getId() + "#SUM:" + booking.getFinalPrice(), 300);

            String confirmId = FileUploader.uploadImage(confirmQr, "confirm_" + booking.getId() + ".png");
            String paymentId = FileUploader.uploadImage(paymentQr, "payment_" + booking.getId() + ".png");

            booking.setQrConfirmationId(confirmId);
            booking.setQrPaymentId(paymentId);
            bookingRepository.save(booking);

        } catch (Exception error) {
            throw new RuntimeException("не удалось создать qr-код", error);
        }

        return BookingResponse.builder()
                .id(booking.getId())
                .firstName(booking.getFirstName())
                .lastName(booking.getLastName())
                .phone(booking.getPhone())
                .email(booking.getEmail())
                .pcId(booking.getPcId())
                .startTime(booking.getStartTime())
                .endTime(booking.getEndTime())
                .paymentMethod(booking.getPaymentMethod())
                .finalPrice(booking.getFinalPrice())
                .qrConfirmationId(booking.getQrConfirmationId())
                .qrPaymentId(booking.getQrPaymentId())
                .build();
    }

    public List<BookingTimeDto> getBookingsByPcAndDate(String pcId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        return bookingRepository.findByPcIdAndDate(pcId, startOfDay, endOfDay)
                .stream()
                .map(b -> new BookingTimeDto(b.getStartTime(), b.getEndTime()))
                .collect(Collectors.toList());
    }

    public List<Booking> getBookingsByEmail(String email) {
        return bookingRepository.findAllByEmail(email);
    }
}
