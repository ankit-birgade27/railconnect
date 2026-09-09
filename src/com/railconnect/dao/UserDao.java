package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.User;

public class UserDao {
	
	
	  private List<User> users = new ArrayList<>();
	  
	  
	public  void registerUser(User user) {
			  
	  }
	  
	 public User findById(int userId) {

	        for (User user : users) {

	            if (user.getUserId() == userId) {

	                return user;
	            }
	        }

	        return null;
	    }


	   
	    public void updateUser(User user) {

	        for (int i = 0; i < users.size(); i++) {

	            if (users.get(i).getUserId() == user.getUserId()) {

	                users.set(i, user);

	                return;
	            }
	        }
	    }  
	  

}
