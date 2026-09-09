package com.railconnect.service;

import java.math.BigDecimal;

public interface CancellationService {

    void cancelBooking(String bookingId);

    BigDecimal calculateRefund(String bookingId);

    boolean canCancelBooking(String bookingId);

    String getCancellationStatus(String bookingId);
}