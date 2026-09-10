package com.railconnect.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.lang.Exception;


import com.railconnect.dao.UserDao;
import com.railconnect.model.Passenger;
import com.railconnect.model.User;
import com.railconnect.service.UserService;

public class UserServiceImpl implements UserService {


    private UserDao userDao;
    
    

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
	
	
	@Override
	public void registerUser(User user) {
		
	}

	@Override
	public User viewProfile(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProfile(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(int userId, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forgotPassword(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPassenger(Passenger passenger) {
		
		//checks passenger is null
		if (passenger == null) {
			throw new IllegalArgumentException("Passenger cannot be null");
		}
		
		//checks user is null
		if(passenger.getUser()==null) {
			throw new IllegalArgumentException("user cannot be null");
		}
		
		//get userId from user
	    int userId=passenger.getUser().getUserId();
	 
		if( userDao.findById(userId)==null) {
			throw new NoSuchElementException("User not found with ID: " + userId);
		}
		
		//validation checks
		if (passenger.getFirstName() == null ) {
	        throw new IllegalArgumentException("Passenger name cannot be empty");
	    }
		if (passenger.getAge()<=0 ) {
	        throw new IllegalArgumentException("Passenger age is not valid");
	    }
		if (passenger.getGender() == null ) {
	        throw new IllegalArgumentException("Passenger gender cannot be empty");
	    }
		
		if (passenger.getPassengerType() == null ) {
	        throw new IllegalArgumentException("Passengertype cannot be empty");
	    }
		if (passenger.getIdProofNumber() == null ) {
	        throw new IllegalArgumentException("Passenger IdProofNumber cannot be null");
	    }
		if (passenger.getIdProofType() == null ) {
	        throw new IllegalArgumentException("Passenger IdProofType cannot be null");
	    }
		
		userDao.savePassenger(passenger);
	}

	@Override
	public void updatePassenger(Passenger passenger) {

		if (passenger == null) {
	        throw new IllegalArgumentException("Passenger cannot be null");
	    }

	   
	    int passengerId = passenger.getPassengerId(); // Adjust method name if it's getId()
	    if (passengerId <= 0) {
	        throw new IllegalArgumentException("Invalid passenger ID provided");
	    }

	   
	    Passenger existingPassenger = userDao.findPassengerById(passengerId);
	    if (existingPassenger == null) {
	        throw new java.util.NoSuchElementException("Passenger not found with ID: " + passengerId);
	    }


	    if (passenger.getFirstName() == null) {
	        throw new IllegalArgumentException("Passenger name cannot be empty");
	    }
	    
	    if (passenger.getAge() <= 0 || passenger.getAge() > 100) {
	        throw new IllegalArgumentException("Passenger age must be valid");
	    }

	    // Update the passenger using UserDao
	    userDao.updatePassenger(passenger);
		
	}

	@Override
	public void deletePassenger(int passengerId) {
		
		 if (passengerId <= 0) {
		        throw new IllegalArgumentException("Invalid passenger ID provided");
		    }
		 
		 Passenger existingPassenger = userDao.findPassengerById(passengerId);
		    if (existingPassenger == null) {
		        throw new java.util.NoSuchElementException("Passenger not found with ID: " + passengerId);
		    }
     
		    userDao.deletePassenger(passengerId);

		
	}

	@Override
	public List<Passenger> getPassengers(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getBookingHistory(int userId) {
		// TODO Auto-generated method stub
		return null;
	}


}
