package com.railconnect.serviceimpl;

import java.math.BigDecimal;

import com.railconnect.dao.CancellationDao;
import com.railconnect.service.CancellationService;



public class CancellationServiceImpl implements CancellationService{
	
	 private CancellationDao cancellationDao;

	    public CancellationServiceImpl(CancellationDao cancellationDao) {
	        this.cancellationDao = cancellationDao;
	    }

	@Override
	public void cancelBooking(String bookingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal calculateRefund(String bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canCancelBooking(String bookingId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCancellationStatus(String bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

}
