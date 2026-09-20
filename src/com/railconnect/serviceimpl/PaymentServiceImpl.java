package com.railconnect.serviceimpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.railconnect.dao.PaymentDao;
import com.railconnect.enums.PaymentStatus;
import com.railconnect.exception.BookingNotFoundException;
import com.railconnect.exception.InvalidBookingIdException;
import com.railconnect.exception.InvalidPaymentAmountException;
import com.railconnect.exception.PaymentFailedException;
import com.railconnect.model.Booking;
import com.railconnect.model.Payment;
import com.railconnect.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    private PaymentDao paymentDao;

    public PaymentServiceImpl() {
        this.paymentDao = new PaymentDao();
    }

    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    /**
     * Group 1 Assigned Method: Processes a payment for a booking and returns the Payment object.
     *
     * Required Processing:
     * 1. Check whether booking ID is provided.
     * 2. Check whether amount is provided.
     * 3. Validate that payment amount is positive.
     * 4. Check whether the referenced booking exists.
     * 5. Create Payment object using booking and amount information.
     * 6. Set payment method and payment status.
     * 7. Generate or assign transaction information.
     * 8. Save payment using PaymentDao.
     * 9. Return created Payment object.
     */
    @Override
    public Payment makePayment(String bookingId, BigDecimal amount) {
        // Step 1: Check whether booking ID is provided
        if (bookingId == null || bookingId.trim().isEmpty()) {
            throw new InvalidBookingIdException("Booking ID is required and cannot be null or blank.");
        }

        // Step 2 & 3: Check whether amount is provided and positive
        if (amount == null) {
            throw new InvalidPaymentAmountException("Payment amount is required and cannot be null.");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPaymentAmountException("Payment amount must be a positive number. Provided: " + amount);
        }

        // Step 4: Check whether the referenced booking exists
        String trimmedBookingId = bookingId.trim();
        Booking booking = paymentDao.findBookingById(trimmedBookingId);
        if (booking == null) {
            throw new BookingNotFoundException("Booking not found with ID: " + bookingId);
        }

        // Step 5: Create Payment object using booking and amount information
        String paymentId = "PAY" + System.currentTimeMillis() + (int)(Math.random() * 900 + 100);
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setBooking(booking);
        payment.setAmount(amount);

        // Step 6: Set payment method and status
        payment.setPaymentMethod("UPI");
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDate(LocalDateTime.now());

        // Update booking with payment reference and status
        booking.setPayment(payment);
        booking.setBookingStatus("CONFIRMED");

        // Step 7: Generate or assign transaction information
        String transactionId = "TXN" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
        payment.setTransactionId(transactionId);

        // Step 8: Save payment using PaymentDao
        try {
            paymentDao.savePayment(payment);
        } catch (Exception e) {
            payment.setStatus(PaymentStatus.FAILED);
            throw new PaymentFailedException("Payment processing failed: " + e.getMessage(), e);
        }

        // Step 9: Return created Payment object
        return payment;
    }

    @Override
    public Payment getPaymentById(String paymentId) {
        if (paymentId == null || paymentId.trim().isEmpty()) {
            return null;
        }
        return paymentDao.findById(paymentId.trim());
    }

    @Override
    public Payment getPaymentByBookingId(String bookingId) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            return null;
        }
        return paymentDao.findByBookingId(bookingId.trim());
    }

    @Override
    public boolean isPaymentSuccessful(String paymentId) {
        Payment payment = getPaymentById(paymentId);
        return payment != null && PaymentStatus.SUCCESS.equals(payment.getStatus());
    }

    @Override
    public void refundPayment(String paymentId) {
        Payment payment = getPaymentById(paymentId);
        if (payment != null) {
            payment.setStatus(PaymentStatus.REFUNDED);
            paymentDao.savePayment(payment);
        }
    }
}
