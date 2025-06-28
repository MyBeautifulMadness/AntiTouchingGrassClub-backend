package com.example.antitouch.dto;

import com.example.antitouch.enums.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String pcId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private PaymentMethod paymentMethod;
    private String finalPrice;
    private String qrConfirmationId;
    private String qrPaymentId;
}
