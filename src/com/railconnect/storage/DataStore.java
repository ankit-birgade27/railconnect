package com.railconnect.storage;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.Session;
import com.railconnect.model.User;

public class DataStore {
	private static final List<User> users = new ArrayList<>();
	private static final List<Session> sessions = new ArrayList<>();
	
	public static List<User> getUsers(){
		return users;
	}
	
	
	public static List<Session> getSessions(){
		return sessions;
	}
	
	public static void clearSessions() {
	    sessions.clear();
	}
	
}
