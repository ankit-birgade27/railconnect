package com.railconnect.serviceimpl;

import java.math.BigDecimal;

import com.railconnect.dao.FareDao;
import com.railconnect.model.Fare;
import com.railconnect.model.Train;
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
	    // 1. Validate train ID
	    if (trainId <= 0) {
	        throw new IllegalArgumentException("Invalid train ID");
	    }

	    // 2. Check whether train exists
	    Train train = fareDao.findTrainById(trainId);

	    if (train == null) {
	        throw new RuntimeException("Train not found");
	    }

	    // 3. Find fare information
	    Fare fare = fareDao.findByTrainId(trainId);

	    // 4. Check fare exists
	    if (fare == null) {
	        throw new RuntimeException("Fare not found");
	    }

	    // 5. Retrieve and validate base fare
	    BigDecimal baseFare = fare.getBaseFare();

	    if (baseFare == null || baseFare.compareTo(BigDecimal.ZERO) < 0) {
	        throw new IllegalArgumentException("Invalid base fare");
	    }

	    // 6. Return base fare
	    return baseFare;
	}

	@Override
	public BigDecimal getTotalFare(BigDecimal fare, int numberOfPassengers) {
		// TODO Auto-generated method stub
		return null;
	}

}
