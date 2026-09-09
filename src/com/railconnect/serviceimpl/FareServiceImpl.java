package com.railconnect.serviceimpl;

import java.math.BigDecimal;

import com.railconnect.dao.FareDao;
import com.railconnect.service.FareService;

public class FareServiceImpl  implements FareService{
	
	private FareDao fareDao;

    public FareServiceImpl(FareDao fareDao) {
        this.fareDao = fareDao;
    }

	@Override
	public BigDecimal calculateFare(int trainId, String source, String destination, int numberOfPassengers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calculatePassengerFare(int trainId, String source, String destination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBaseFare(int trainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getTotalFare(BigDecimal fare, int numberOfPassengers) {
		// TODO Auto-generated method stub
		return null;
	}

}
