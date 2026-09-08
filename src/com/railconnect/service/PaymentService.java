package com.railconnect.service;

import java.math.BigDecimal;

import com.railconnect.model.Payment;

public interface PaymentService {

    Payment makePayment(String bookingId,
                        BigDecimal amount);

    Payment getPaymentById(String paymentId);

    Payment getPaymentByBookingId(String bookingId);

    boolean isPaymentSuccessful(String paymentId);

    void refundPayment(String paymentId);
}