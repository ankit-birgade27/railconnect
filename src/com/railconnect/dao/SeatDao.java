package com.railconnect.dao;


import java.util.ArrayList;

import java.util.List;

import com.railconnect.model.Seat;
import com.railconnect.model.Coach;
import com.railconnect.model.Train;


import java.util.List;

import com.railconnect.model.Seat;
import com.railconnect.storage.DataStore;

public class SeatDao {
	
	   public Seat findById(String seatId) {
		   List<Seat> seats = DataStore.getSeats();
		   
		   for(Seat seat : seats) {
			   if(seat.getSeatId().equals(seatId)) {
				   return seat;
			   }
		   }
		   
		   return null;
	   }
	   
	   public void updateSeat(Seat seat) {
		   List<Seat> seats = DataStore.getSeats();
		   
		   for(int i = 0; i < seats.size(); i++) {
			   Seat existSeat = seats.get(i);
			   
			   if(existSeat.getSeatId().equals(seat.getSeatId())) {
				   seats.set(i, seat);
			   }
		   }
	   


		  
		  
	   }
}
