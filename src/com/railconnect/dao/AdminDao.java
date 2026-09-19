package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.Train;
import com.railconnect.model.User;
import com.railconnect.storage.DataStore;

public class AdminDao {
	  
	    // Find user by ID
	    public User findById(int userId) {

	        for (User user : DataStore.getUsers()) {

	            if (user.getUserId() == userId) {
	                return user;
	            }
	        }

	        return null;
	    }

	    // Get all users
	    public List<User> findAllUsers() {

	        return new ArrayList<>(DataStore.getUsers());
	    }

	    // Delete user by ID
	    public void deleteUser(int userId) {
	    		List<User> users = DataStore.getUsers();	
	    		users.removeIf(user -> user.getUserId() == userId);
	    }
	    
	    
	    public List<Train> findAllTrains(){
	    		return new ArrayList<>(DataStore.getTrains());
	    }

	}


