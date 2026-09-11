package com.railconnect.serviceimpl;

import java.util.List;
import com.railconnect.exception.InvalidPassengerException;
import com.railconnect.exception.PassengerNotFoundException;
import com.railconnect.exception.UnauthorizedAccessException;
import com.railconnect.exception.UserNotFoundException;

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
	public void addPassenger(Passenger passenger) 
		throws UserNotFoundException, InvalidPassengerException {

		  if (passenger == null) {
		    throw new InvalidPassengerException("Passenger cannot be null");
		    }

		  if (passenger.getPassengerId() <= 0) {
			  throw new InvalidPassengerException("Invalid passenger ID");
			  }

		  if (passenger.getUser() == null) {
			  throw new InvalidPassengerException("User cannot be null");
			  }
		  
		  int userId = passenger.getUser().getUserId();

		  if (userDao.findById(userId) == null) {
			  throw new UserNotFoundException("User not found with ID: " + userId);
			  }
                
		  if (passenger.getFirstName() == null ||
				  passenger.getFirstName().trim().isEmpty()) {
              throw new InvalidPassengerException( "First name cannot be empty");
		  }

		  if (passenger.getLastName() == null ||
				 passenger.getLastName().trim().isEmpty()) {
			  throw new InvalidPassengerException("Last name cannot be empty");
			  }

		  if (passenger.getAge() <= 0) {
			  throw new InvalidPassengerException("Age must be greater than 0");
			  }
		            
		  if (passenger.getGender() == null ||
				  passenger.getGender().trim().isEmpty()) {
			  throw new InvalidPassengerException("Gender cannot be empty");
		                }

		  if (passenger.getPassengerType() == null ||
				  passenger.getPassengerType().trim().isEmpty()) {
			  throw new InvalidPassengerException("Passenger type cannot be empty");
			  }
		                
		  if (passenger.getIdProofType() == null ||
				  passenger.getIdProofType().trim().isEmpty()) {
			  throw new InvalidPassengerException("ID proof type cannot be empty");
			  }

		  if (passenger.getIdProofNumber() == null ||
				  passenger.getIdProofNumber().trim().isEmpty()) {
			  throw new InvalidPassengerException("ID proof number cannot be empty");
		                    }

		  userDao.savePassenger(passenger);

		   System.out.println("Passenger added successfully.");
		   

	}

	@Override
	public void updatePassenger(Passenger passenger)  
			throws PassengerNotFoundException,  InvalidPassengerException,
			UnauthorizedAccessException {

		if (passenger == null) {
		throw new InvalidPassengerException("Passenger cannot be null");
		}
		
		if (passenger.getPassengerId() <= 0) {
		throw new InvalidPassengerException("Invalid passenger ID");
		}
		
		Passenger existingPassenger =userDao.findPassengerById(passenger.getPassengerId());
		
		if (existingPassenger == null) {
		throw new PassengerNotFoundException("Passenger not found with ID: "
		     + passenger.getPassengerId());
		}
		
		if (passenger.getUser() == null) {
		throw new UnauthorizedAccessException("User information is required");
		}
		
		if (existingPassenger.getUser() == null) {
		throw new UnauthorizedAccessException("Passenger has no associated user");
		}
		
		if (existingPassenger.getUser().getUserId()
		 != passenger.getUser().getUserId()) {
		
		throw new UnauthorizedAccessException("You are not authorized to update this passenger");
		}
		
		if (passenger.getFirstName() == null ||
		passenger.getFirstName().trim().isEmpty()) {
		
		throw new InvalidPassengerException("First name cannot be empty");
		}
		
		if (passenger.getLastName() == null ||
		passenger.getLastName().trim().isEmpty()) {
		
		throw new InvalidPassengerException("Last name cannot be empty");
		}
		
		if (passenger.getAge() <= 0) {
		throw new InvalidPassengerException("Age must be greater than 0");
		}
		
		if (passenger.getGender() == null ||
		passenger.getGender().trim().isEmpty()) {
		
		throw new InvalidPassengerException("Gender cannot be empty");
		}
		
		if (passenger.getPassengerType() == null ||
		passenger.getPassengerType().trim().isEmpty()) {
		
		throw new InvalidPassengerException("Passenger type cannot be empty");
		}
		
		if (passenger.getIdProofType() == null ||
		passenger.getIdProofType().trim().isEmpty()) {
		
		throw new InvalidPassengerException("ID proof type cannot be empty");
		}
		
		if (passenger.getIdProofNumber() == null ||
		passenger.getIdProofNumber().trim().isEmpty()) {
		
		throw new InvalidPassengerException("ID proof number cannot be empty");
		}


	    // Update the passenger using UserDao
	    userDao.updatePassenger(passenger);
	    
	    System.out.println("Passenger updaded successfully");
		
	}

	@Override
	public void deletePassenger(int passengerId)  
			throws PassengerNotFoundException,UnauthorizedAccessException {

		if (passengerId <= 0) {
			throw new PassengerNotFoundException("Invalid passenger ID");
			}

		Passenger passenger =userDao.findPassengerById(passengerId);

		if (passenger == null) {
			throw new PassengerNotFoundException("Passenger not found with ID: " + passengerId);
			}

		userDao.deletePassenger(passengerId);

		System.out.println("Passenger deleted successfully");
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
