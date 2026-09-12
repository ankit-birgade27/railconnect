package com.railconnect.dao;
import java.util.List;

import com.railconnect.model.Session;
import com.railconnect.model.User;
import com.railconnect.storage.DataStore;

public class AuthenticationDao {

	public User findByUsername(String username) {
	    if (username == null) {
	        return null;
	    }

	    List<User> users = DataStore.getUsers();

	    for (User user : users) {
	        if (user.getUsername() != null &&
	            user.getUsername().equalsIgnoreCase(username.trim())) {
	            return user;
	        }
	    }

	    return null;
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
	
	
	public void updateLoginAttempts(int userId, int attempts) {
		User user = findById(userId);
		
		if(user != null) {
			user.setLoginAttempts(attempts);
		}
		
	}
	
	public void updateAccountStatus(int userId, boolean locked) {
		User user = findById(userId);
		
		if(user != null) {
			user.setAccountLocked(locked);;
		}
	}

	public void saveSession(Session session) {
		DataStore.getSessions().add(session);
	}

	public Session findActiveSessionByUserId(int userId) {
		
		List<Session> sessions = DataStore.getSessions();
		for(Session session: sessions) {
			if(session.getUser().getUserId() == userId && session.isActive()) {
				return session;
			}
		}
		return null;
	}

	public void invalidateSession(int userId) {
		Session session = findActiveSessionByUserId(userId);
		
		if(session != null) {
			session.setActive(false);
			session.setLogoutTime(java.time.LocalDateTime.now());
		}
	}
}
