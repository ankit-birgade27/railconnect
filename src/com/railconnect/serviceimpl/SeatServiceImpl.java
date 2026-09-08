package com.railconnect.serviceimpl;

import java.util.List;

import com.railconnect.dao.SeatDao;
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
		
	}

}
