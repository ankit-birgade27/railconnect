package com.railconnect.serviceimpl;

import com.railconnect.model.Session;
import com.railconnect.model.User;
import com.railconnect.service.AuthenticationService;

import java.time.LocalDateTime;
import java.util.UUID;

import com.railconnect.dao.AuthenticationDao;
import com.railconnect.exception.AccountLockedException;
import com.railconnect.exception.InvalidCredentialsException;
import com.railconnect.exception.SessionNotFoundException;
import com.railconnect.exception.UserNotFoundException;

public class AuthenticationServiceImpl  implements AuthenticationService{
	
	private final AuthenticationDao authenticationDao;
	
	public AuthenticationServiceImpl() {
		this.authenticationDao = new AuthenticationDao();
	}

	@Override
	public User login(String username, String password) {
		User user = authenticationDao.findByUsername(username);
		if(user == null) {
			throw new UserNotFoundException("User not found.");
		}
		
		if(user.isAccountLocked()) {
			throw new AccountLockedException("User account is locked");
		}
		
		if(!user.getPassword().equals(password)) {
			int attempts = user.getLoginAttempts()+1;
			authenticationDao.updateLoginAttempts(user.getUserId(), attempts);
			
			if(attempts>=3) {
				authenticationDao.updateAccountStatus(user.getUserId(), true);
				
				throw new AccountLockedException(
                        "Account locked due to 3 failed login attempts."
                );
			}
			
			throw new InvalidCredentialsException(
                    "Invalid username or password."
            );
		}
		
		authenticationDao.updateLoginAttempts(user.getUserId(), 0);
		
		Session session = new Session(UUID.randomUUID().toString(), user, LocalDateTime.now(), null, true);
		
		authenticationDao.saveSession(session);
		
		return user;
	}

	@Override
	public void logout(int userId) {
		Session session = authenticationDao.findActiveSessionByUserId(userId);
		
		if(session == null) {
			throw new SessionNotFoundException("No active session found for user.");
		}
		
		authenticationDao.invalidateSession(userId);
		
	}

	@Override
	public boolean validateUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateMobile(String mobile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validatePassword(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountLocked(int userId) {
		User user = authenticationDao.findById(userId);
		
		if(user == null) {
			return false;
		}
		
		return user.isAccountLocked();
	}

	@Override
	public int getLoginAttempts(int userId) {
User user = authenticationDao.findById(userId);
		
		if(user == null) {
			return 0;
		}
		
		return user.getLoginAttempts();
	}

}
