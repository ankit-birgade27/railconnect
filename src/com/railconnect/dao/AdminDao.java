package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.Train;
import com.railconnect.model.User;

public class AdminDao {
	
	  private List<User> users = new ArrayList<>();

	  private List<Train> trains = new ArrayList<>();
	  
	    // Find user by ID
	    public User findById(int userId) {

	        for (User user : users) {

	            if (user.getUserId() == userId) {
	                return user;
	            }
	        }

	        return null;
	    }

	    // Get all users
	    public List<User> findAllUsers() {

	        return new ArrayList<>(users);
	    }

	    // Delete user by ID
	    public void deleteUser(int userId) {

	        users.removeIf(user -> user.getUserId() == userId);
	    }

	}


