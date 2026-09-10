package com.railconnect.dao;
import java.util.List;

import com.railconnect.model.User;
import com.railconnect.storage.DataStore;

public class AuthenticationDao {
	
	public User findByUsername(String username) {
		List<User> users = DataStore.getUsers();
		for(User user: users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	
	
	public void updateLoginAttempts(int userId, int attempts) {
		User user = findById(userId);
		if(user != null) {
			user.setLoginAttempts(attempts);
		}
	}
	
	public void updateAccountStatus(int userId, boolean isLocked) {
	    User user = findById(userId);

	    if (user != null) {
	        user.setAccountLocked(isLocked);
	    }
	}
	
	public User findById(int userId) {
		List<User> users = DataStore.getUsers();
		for(User user: users) {
			if(user.getUserId() == userId) {
				return user;
			}
		}
		
		return null;
	}

}
