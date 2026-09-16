package com.railconnect.serviceimpl;

import java.util.List;
import com.railconnect.exception.*;

import com.railconnect.dao.SeatDao;
import com.railconnect.dao.TrainDao;
import com.railconnect.enums.SeatStatus;
import com.railconnect.exception.InvalidSeatIdException;
import com.railconnect.exception.SeatAlreadyAvailableException;
import com.railconnect.exception.SeatNotFoundException;
import com.railconnect.model.Seat;
import com.railconnect.model.Train;
import com.railconnect.service.SeatService;

public class SeatServiceImpl implements SeatService{
	
	   private SeatDao seatDao;

	    public SeatServiceImpl(SeatDao seatDao) {
	        this.seatDao = seatDao;
	    }

	@Override
	public List<Seat> getAvailableSeats(int trainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seat getSeatById(String seatId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seat getSeatByNumber(int trainId, String seatNumber) {
	    // 1. Validate Train ID
	    if (trainId <= 0) {
	        throw new InvalidTrainIdException(
	                "Train ID must be positive");
	    }

	    // 2. Validate Seat Number
	    if (seatNumber == null || seatNumber.trim().isEmpty()) {
	        throw new InvalidSeatNumberException(
	                "Seat number cannot be empty");
	    }

	    // 3. Check whether train exists
	    TrainDao trainDao = new TrainDao();

	    Train train = trainDao.findById(trainId);

	    if(train == null) {
	        throw new TrainNotFoundException(
	                "Train not found with ID: " + trainId);
	    }

	    // 4. Search seat
	    Seat seat = seatDao.findByTrainIdAndSeatNumber(
	            trainId,
	            seatNumber.trim());

	    // 5. Check whether seat exists
	    if (seat == null) {
	        throw new SeatNotFoundException(
	                "Seat not found with number: " + seatNumber);
	    }

	    // 6. Return seat
	    return seat;
	}

	@Override
	public boolean isSeatAvailable(int trainId, String seatNumber) 
	throws InvalidSeatNumberException,SeatNotFoundException,
	InvalidTrainIdException,TrainNotFoundException{
		
		 if (trainId <= 0) {
		        throw new InvalidTrainIdException("Train ID must be positive");
		    }
		 
		 if (seatNumber == null || seatNumber.trim().isEmpty()) {
		        throw new InvalidSeatNumberException("Seat number cannot be empty");
		    }
		 
		 if (!seatDao.trainExists(trainId)) {
	            throw new TrainNotFoundException(
	                    "Train not found with this ID: " + trainId);
	        }
		 
		 Seat seat = seatDao.findByTrainIdAndSeatNumber(trainId, seatNumber);

		  if (seat == null) {
		        throw new SeatNotFoundException("Seat not found");
		    }
		 
		  return "AVAILABLE".equalsIgnoreCase(seat.getStatus());
		 
	}

	@Override
	public void reserveSeat(String seatId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releaseSeat(String seatId) {
		// TODO Auto-generated method stub
		
		if(seatId == null || seatId.isBlank()) {
			throw new InvalidSeatIdException("Invalid Seat ID");
		}
		
		Seat seat = seatDao.findById(seatId);
		
		if(seat == null) {
			throw new SeatNotFoundException("Seat not found");
		}
		
		if(SeatStatus.AVAILABLE.name().equals(seat.getStatus())) {
			throw new SeatAlreadyAvailableException("Seat is already Available.");
		}
		
		seat.setStatus(SeatStatus.AVAILABLE.name());
		
		seatDao.updateSeat(seat);
		
	}

}
