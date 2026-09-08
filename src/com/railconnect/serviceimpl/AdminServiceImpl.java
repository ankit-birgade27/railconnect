package com.railconnect.serviceimpl;

import java.util.List;

import com.railconnect.dao.AdminDao;
import com.railconnect.model.Train;
import com.railconnect.model.User;
import com.railconnect.service.AdminService;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
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
