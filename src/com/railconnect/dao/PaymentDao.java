package com.railconnect.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.railconnect.model.Booking;
import com.railconnect.model.Payment;

public class PaymentDao {

    private List<Payment> payments = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    /**
     * Finds a booking by its booking ID.
     */
    public synchronized Booking findBookingById(String bookingId) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            return null;
        }
        String trimmed = bookingId.trim();
        for (Booking booking : bookings) {
            if (booking != null && booking.getBookingId() != null &&
                    booking.getBookingId().trim().equalsIgnoreCase(trimmed)) {
                return booking;
            }
        }
        return null;
    }

    /**
     * Saves or updates a booking in the collection.
     */
    public synchronized void saveBooking(Booking booking) {
        if (booking == null || booking.getBookingId() == null) {
            return;
        }
        String trimmedId = booking.getBookingId().trim();
        for (int i = 0; i < bookings.size(); i++) {
            Booking b = bookings.get(i);
            if (b != null && b.getBookingId() != null &&
                    b.getBookingId().trim().equalsIgnoreCase(trimmedId)) {
                bookings.set(i, booking);
                return;
            }
        }
        bookings.add(booking);
    }

    /**
     * Saves or updates a payment in the collection.
     */
    public synchronized void savePayment(Payment payment) {
        if (payment == null || payment.getPaymentId() == null) {
            return;
        }
        String trimmedId = payment.getPaymentId().trim();
        for (int i = 0; i < payments.size(); i++) {
            Payment p = payments.get(i);
            if (p != null && p.getPaymentId() != null &&
                    p.getPaymentId().trim().equalsIgnoreCase(trimmedId)) {
                payments.set(i, payment);
                return;
            }
        }
        payments.add(payment);
    }

    /**
     * Finds a payment by its payment ID.
     */
    public synchronized Payment findById(String paymentId) {
        if (paymentId == null || paymentId.trim().isEmpty()) {
            return null;
        }
        String trimmed = paymentId.trim();
        for (Payment payment : payments) {
            if (payment != null && payment.getPaymentId() != null &&
                    payment.getPaymentId().trim().equalsIgnoreCase(trimmed)) {
                return payment;
            }
        }
        return null;
    }

    /**
     * Finds a payment associated with a booking ID.
     */
    public synchronized Payment findByBookingId(String bookingId) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            return null;
        }
        String trimmed = bookingId.trim();
        for (Payment payment : payments) {
            if (payment != null && payment.getBooking() != null &&
                    payment.getBooking().getBookingId() != null &&
                    payment.getBooking().getBookingId().trim().equalsIgnoreCase(trimmed)) {
                return payment;
            }
        }
        return null;
    }

    /**
     * Retrieves all saved payments.
     */
    public synchronized List<Payment> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(payments));
    }

    /**
     * Clears all payments and bookings (used for testing resets).
     */
    public synchronized void clear() {
        payments.clear();
        bookings.clear();
    }
}
