package com.railconnect.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
	  




    private static final AtomicInteger idGenerator = new AtomicInteger(1001);

    public synchronized void saveUser(User user) {
        if (user == null) {
            return;
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == user.getUserId()) {
                users.set(i, user);
                return;
            }
        }
        users.add(user);
    }

    public synchronized User findByUsername(String username) {
        if (username == null) {
            return null;
        }
        String trimmed = username.trim();
        for (User u : users) {
            if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(trimmed)) {
                return u;
            }
        }
        return null;
    }

    public synchronized User findByEmail(String email) {
        if (email == null) {
            return null;
        }
        String trimmed = email.trim();
        for (User u : users) {
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(trimmed)) {
                return u;
            }
        }
        return null;
    }

    public synchronized User findByMobile(String mobile) {
        if (mobile == null) {
            return null;
        }
        String trimmed = mobile.trim();
        for (User u : users) {
            if (u.getMobile() != null && u.getMobile().trim().equals(trimmed)) {
                return u;
            }
        }
        return null;
    }

  
    public synchronized List<User> findAllUsers() {
        return Collections.unmodifiableList(new ArrayList<>(users));
    }

    public int generateUniqueUserId() {
        return idGenerator.getAndIncrement();
    }

    public String getnewMessage(String msg) {
        return msg;
    }

    public synchronized void clear() {
        users.clear();
        idGenerator.set(1001);
    }
}
