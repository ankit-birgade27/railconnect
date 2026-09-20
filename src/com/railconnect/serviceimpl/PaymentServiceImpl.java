package com.railconnect.serviceimpl;

import java.math.BigDecimal;

import com.railconnect.dao.PaymentDao;
import com.railconnect.exception.InvalidPaymentIdException;
import com.railconnect.exception.PaymentNotFoundException;
import com.railconnect.model.Payment;
import com.railconnect.service.PaymentService;

public class PaymentServiceImpl  implements PaymentService {
	
	   private PaymentDao paymentDao;

	    public PaymentServiceImpl(PaymentDao paymentDao) {
	        this.paymentDao = paymentDao;
	    }

	@Override
	public Payment makePayment(String bookingId, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment getPaymentById(String paymentId) 
		throws InvalidPaymentIdException , PaymentNotFoundException{
		// TODO Auto-generated method stub
		 // 1. Check Payment ID
		if(paymentId==null || paymentId.trim().isEmpty()) {
			throw new InvalidPaymentIdException(
				"Payment ID cannot be null or empty");
		}
			// 2. Search Payment using DAO
	        Payment payment = paymentDao.findById(paymentId.trim());

	        // 3. Check whether Payment exists
	        if (payment == null) {
	            throw new PaymentNotFoundException(
	                    "Payment not found with ID: " + paymentId);
	        }
	        // 4. Return matching Payment
	        return payment;
	    }

	@Override
	public Payment getPaymentByBookingId(String bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPaymentSuccessful(String paymentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void refundPayment(String paymentId) {
		// TODO Auto-generated method stub
		
	}

}
