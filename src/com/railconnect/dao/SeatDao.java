package com.railconnect.dao;

import java.util.ArrayList;

import java.util.List;

import com.railconnect.model.Seat;
import com.railconnect.model.Coach;
import com.railconnect.model.Train;

public class SeatDao {
	
	   private List<Seat> seats = new ArrayList<>();
	   
	   private TrainDao trainDao;

	    public SeatDao(TrainDao trainDao) {
	        this.trainDao = trainDao;
	    }

	    public boolean trainExists(int trainId) {
	        return trainDao.findById(trainId) != null;
	    }

	  public Seat findByTrainIdAndSeatNumber(int trainId, String seatNumber) {
		  Train train = trainDao.findById(trainId);

	        if (train == null) {
	            return null;
	        }

	        if (train.getCoaches() == null) {
	            return null;
	        }

	        for (Coach coach : train.getCoaches()) {

	            if (coach.getSeats() == null) {
	                continue;
	            }

	            for (Seat seat : coach.getSeats()) {

	                if (seat.getSeatNumber() != null &&
	                    seat.getSeatNumber().equals(seatNumber)) {

	                    return seat;
	                }
	            }
	        }

		   return null;
	   }
}
