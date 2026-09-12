package com.railconnect.serviceimpl;

import com.railconnect.dao.AuthenticationDao;
import com.railconnect.exception.AccountLockedException;
import com.railconnect.exception.InvalidCredentialsException;
import com.railconnect.exception.UserNotFoundException;
import com.railconnect.model.User;
import com.railconnect.service.AuthenticationService;

public class AuthenticationServiceImpl  implements AuthenticationService{
	
	private AuthenticationDao authenticationDao;
	
	public AuthenticationServiceImpl(AuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}

	@Override
	public User login(String username, String password) {
		User user = authenticationDao.findByUsername(username);
		if(user == null) {
			throw new UserNotFoundException("User not found");
		}
		
		if(user.isAccountLocked()) {
			throw new AccountLockedException("Your account name:"+ username+ " is locked");
		}
		
		if(!user.getPassword().equals(password)) {
			int attempts = user.getLoginAttempts();
			attempts = attempts + 1;
			authenticationDao.updateLoginAttempts(user.getUserId(), attempts);
			if(attempts >=3) {
				authenticationDao.updateAccountStatus(user.getUserId(), true);
			}
			throw new InvalidCredentialsException("Wrong Password");
		}
		
		authenticationDao.updateLoginAttempts(user.getUserId(), 0);
		return user;
	}

	@Override
	public void logout(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateUsername(String username) {
		if(username == null || username.isBlank()) {
			return false;
		}
<<<<<<< Updated upstream
		
		return username.length() >= 3;
=======
		return username.length()>=3;
>>>>>>> Stashed changes
	}

	@Override
	public boolean validateEmail(String email) {
		if(email == null || email.isBlank()) {
			return false;
		}
<<<<<<< Updated upstream
		
		return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+$");
=======
		return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
>>>>>>> Stashed changes
	}

	@Override
	public boolean validateMobile(String mobile) {
		if(mobile == null || mobile.isBlank()) {
			return false;
		}
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		return mobile.matches("\\d{10}");
	}

	@Override
	public boolean validatePassword(String password) {
<<<<<<< Updated upstream
		
		if(password == null || password.isBlank() || password.length() > 8) {
			return false;
		}
		
		boolean isUppercase = false;
		boolean isLowercase = false;
		boolean isDigit = false;
		
=======
		if(password == null || password.isBlank() || password.length() < 8) {
			return false;
		}

		boolean isUppercase = false;
		boolean isLowercase = false;
		boolean isDigit = false;

>>>>>>> Stashed changes
		for(char ch : password.toCharArray()) {
			if(Character.isUpperCase(ch)) {
				isUppercase = true;
			}
<<<<<<< Updated upstream
			
			
			if(Character.isLowerCase(ch)) {
				isLowercase = true;
			}
			
=======

			if(Character.isLowerCase(ch)) {
				isLowercase = true;
			}

>>>>>>> Stashed changes
			if(Character.isDigit(ch)) {
				isDigit = true;
			}
		}
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		return isUppercase && isLowercase && isDigit;
	}

	@Override
	public boolean isAccountLocked(int userId) {
		User user = authenticationDao.findById(userId);
		if(user == null) {
			throw new UserNotFoundException("User not found");
		}
		
		return user.isAccountLocked();
	}

	@Override
	public int getLoginAttempts(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
