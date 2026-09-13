package com.railconnect.serviceimpl;

import java.util.List;

import com.railconnect.dao.SeatDao;
import com.railconnect.enums.SeatStatus;
import com.railconnect.exception.InvalidSeatIdException;
import com.railconnect.exception.SeatAlreadyAvailableException;
import com.railconnect.exception.SeatNotFoundException;
import com.railconnect.model.Seat;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSeatAvailable(int trainId, String seatNumber) {
		// TODO Auto-generated method stub
		return false;
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
