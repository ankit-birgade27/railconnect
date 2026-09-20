package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.Payment;

public class PaymentDao {

    private List<Payment> payments = new ArrayList<>();

    public Payment findById(String paymentId) {

        for (Payment payment : payments) {

            if (payment != null &&
                payment.getPaymentId().equals(paymentId)) {

                return payment;
            }
        }

        return null;
    }
}