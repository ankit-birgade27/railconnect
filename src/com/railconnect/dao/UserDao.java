package com.railconnect.dao;
import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.User;
import com.railconnect.storage.DataStore;

public class UserDao {
	public void save(User user) {
		DataStore.getUsers().add(user);
	}
	
	public User findById(int userId) {
		List<User> users = DataStore.getUsers();
		for(User user : users) {
			if(user.getUserId() == userId) {
				return user;
			}
		}
		
		return null;
	}
	
	
	public User findByUsername(String username) {
		List<User> users = DataStore.getUsers();
		for(User user: users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	
	public User findByEmail(String email) {
		List<User> users = DataStore.getUsers();
		for(User user: users) {
			if(user.getEmail().equals(email)) {
				return user;
			}
		}
		
		return null;
	}
	
	public User findByMobile(String mobile) {
		List<User> users = DataStore.getUsers();
		for(User user: users) {
			if(user.getMobile().equals(mobile)) {
				return user;
			}
		}
		
		return null;
	}
	
	public List<User> findAll(){
		return new ArrayList<>(DataStore.getUsers());
	}
}
