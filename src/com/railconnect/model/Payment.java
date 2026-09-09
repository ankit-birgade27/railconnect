package com.railconnect.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.railconnect.enums.PaymentStatus;

public class Payment {

    private String paymentId;

    private Booking booking;

    private BigDecimal amount;

    private String paymentMethod;

    private PaymentStatus status;

    private LocalDateTime paymentDate;

    private String transactionId;

    public Payment() {
    }

    public Payment(String paymentId,
                   Booking booking,
                   BigDecimal amount,
                   String paymentMethod,
                   PaymentStatus status,
                   LocalDateTime paymentDate,
                   String transactionId) {

        this.paymentId = paymentId;
        this.booking = booking;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentDate = paymentDate;
        this.transactionId = transactionId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", booking=" + booking +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status=" + status +
                ", paymentDate=" + paymentDate +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}