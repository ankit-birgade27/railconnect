package com.railconnect.serviceimpl;

import java.util.List;

import com.railconnect.dao.AdminDao;
import com.railconnect.model.Train;
import com.railconnect.model.User;
import com.railconnect.service.AdminService;
import com.railconnect.exception.InvalidUserIdException;
import com.railconnect.exception.UserNotFoundException;

public class AdminServiceImpl implements AdminService {
	
	  private AdminDao adminDao;

	    public AdminServiceImpl(AdminDao adminDao) {
	        this.adminDao = adminDao;
	    }

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int userId) {
	    if (userId <= 0) {
	        throw new InvalidUserIdException("User ID must be positive");
	    }

	    User user = adminDao.findById(userId);

	    if (user == null) {
	        throw new UserNotFoundException(
	                "User not found with ID: " + userId);
	    }

	    adminDao.deleteUser(userId);
		
	}

	@Override
	public User getUserById(int userId) {
	    if (userId <= 0) {
	        throw new InvalidUserIdException("User ID must be positive");
	    }

	    User user = adminDao.findById(userId);

	    if (user == null) {
	        throw new UserNotFoundException(
	                "User not found with ID: " + userId);
	    }

		return user;
	}

	@Override
	public List<User> getAllUsers() {

		return adminDao.findAllUsers();
	}

	@Override
	public void addTrain(Train train) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTrain(Train train) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTrain(int trainId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Train> getAllTrains() {
		// TODO Auto-generated method stub
		return null;
	}

}
