package com.railconnect.serviceimpl;

import java.util.List;

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
		 if (userId <= 0) {

	            throw new IllegalArgumentException("Invalid User ID");
	        }


	        User user = userDao.findById(userId);


	        if (user == null) {

	            throw new IllegalArgumentException("User Not Found");
	        }

	        return user;
	}

	@Override
	public void updateProfile(User user) {
		if (user == null) {

            throw new IllegalArgumentException("User cannot be null");
        }


        if (user.getUserId() <= 0) {

            throw new IllegalArgumentException("Invalid User ID");
        }

        User existingUser = userDao.findById(user.getUserId());

        if (existingUser == null) {

            throw new IllegalArgumentException("User Not Found");
        }

        if (user.getUsername() == null ||
            user.getUsername().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Username cannot be empty");
        }


        if (user.getEmail() == null ||
            user.getEmail().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Email cannot be empty");
        }


        if (user.getMobile() == null ||
            user.getMobile().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Mobile cannot be empty");
        }


        if (!user.getMobile().matches("\\d{10}")) {

            throw new IllegalArgumentException(
                    "Mobile number must contain 10 digits");
        }

        existingUser.setUsername(user.getUsername());

        existingUser.setEmail(user.getEmail());

        existingUser.setMobile(user.getMobile());

        userDao.updateUser(existingUser);


        System.out.println("Profile updated successfully.");
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
