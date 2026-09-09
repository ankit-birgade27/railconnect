package com.railconnect.serviceimpl;

import java.util.List;

import com.railconnect.dao.UserDao;
import com.railconnect.exception.DuplicateEmailException;
import com.railconnect.exception.DuplicateMobileException;
import com.railconnect.exception.DuplicateUsernameException;
import com.railconnect.exception.InvalidPasswordException;
import com.railconnect.exception.InvalidUserException;
import com.railconnect.model.Passenger;
import com.railconnect.model.User;
import com.railconnect.service.AuthenticationService;
import com.railconnect.service.UserService;

public class UserServiceImpl implements UserService {


    private UserDao userDao;
    private AuthenticationService authenticationService;
    
    

    public UserServiceImpl(UserDao userDao, AuthenticationService authenticationService) {
        this.userDao = userDao;
        this.authenticationService = authenticationService;
    }
	
	
	@Override
	public void registerUser(User user) {
		if(user == null) {
			throw new InvalidUserException("User cannot be null");
		}
		if (!authenticationService.validateUsername(user.getUsername())) {
		    throw new InvalidUserException("Invalid username");
		}

		if (!authenticationService.validateEmail(user.getEmail())) {
		    throw new InvalidUserException("Invalid email");
		}

		if (!authenticationService.validateMobile(user.getMobile())) {
		    throw new InvalidUserException("Invalid mobile number");
		}

		if (!authenticationService.validatePassword(user.getPassword())) {
		    throw new InvalidPasswordException("Invalid password");
		}
		
		if(userDao.findByUsername(user.getUsername()) != null) {
			throw new DuplicateUsernameException("Username already exist");
		}
		
		if(userDao.findByEmail(user.getEmail()) != null) {
			throw new DuplicateEmailException("Email already exist");
		}
		
		if(userDao.findByMobile(user.getMobile()) != null) {
			throw new DuplicateMobileException("Mobile number already exist");
		}
		
		
		
		int maxId = 0;
		
		for(User existingUser: userDao.findAll()) {
			if(existingUser.getUserId() > maxId) {
				maxId = existingUser.getUserId();
			}
		}
		
		int newId = maxId+1;
		user.setUserId(newId);
		user.setRole("PASSENGER");
		user.setAccountLocked(false);
		user.setLoginAttempts(0);
		
		userDao.save(user);
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePassenger(int passengerId) {
		// TODO Auto-generated method stub
		
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
