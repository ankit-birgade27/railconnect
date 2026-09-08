package com.railconnect.serviceimpl;

import java.math.BigDecimal;

import com.railconnect.dao.PaymentDao;
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
	public Payment getPaymentById(String paymentId) {
		// TODO Auto-generated method stub
		return null;
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
