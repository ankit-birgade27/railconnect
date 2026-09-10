package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.User;

public class UserDao {
	
	
	  private List<User> users = new ArrayList<>();
	  
	  // to find user by their user id 
	  public User findById(int userId) {
		  for(User u : users) {
			  if(u.getUserId() == userId) {
				  return u;
			  }
		  }
		  return null;
	  }
	  
	  // to find user by their email
	  public User findByEmail(String email) {
		  for(User u : users) {
			  if(u.getEmail().equalsIgnoreCase(email)) {
				  return u;
			  }
		  }
		  return null;
	  }
	  
	  
}
