package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.Passenger;
import com.railconnect.model.User;


public class UserDao {
	
	
	  private List<User> users = new ArrayList<>();
	  
	  private List<Passenger> passengers = new ArrayList<>();
	  
	public  void registerUser(User user) {
			  
	  }
	
	public void savePassenger(Passenger passenger) {
		passengers.add( passenger);
	}
	
	public User findById(int userId) {
		for(User user:users) {
			if(user.getUserId()==userId) {
				return user;
			}
		}
		return null;
	}
	
	
	public Passenger findPassengerById(int passengerId) {
     for (Passenger passenger : passengers) {
    	 if (passenger.getPassengerId() == passengerId) {
             return passenger;
    	 }
	}
        return null;	
	}

	public void updatePassenger(Passenger passenger) {
		for (int i = 0;i < passengers.size();i++) {
		
			if(passengers.get(i).getPassengerId() == passenger.getPassengerId()) {
                passengers.set(i, passenger);
                return;
			}
		}
	}
	
	public void deletePassenger(int passengerId) {
		for (int i = 0;i < passengers.size();i++) {
			
			if(passengers.get(i).getPassengerId() == passengerId) {
			passengers.remove(i);
			return;
			}
		}
	}
	  
	  
	  

}
