package com.railconnect.storage;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.User;

public class DataStore {
	
	private static List<User> users = new ArrayList<>();
	
	
	public static List<User> getUsers() {
		return users;
	}

}
