package com.railconnect.serviceimpl;

import com.railconnect.dao.AuthenticationDao;
import com.railconnect.model.User;
import com.railconnect.service.AuthenticationService;

public class AuthenticationServiceImpl  implements AuthenticationService{
	
	private AuthenticationDao authenticationDao;
	
	public AuthenticationServiceImpl(AuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
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
		
		return username.length() >= 3;
	}

	@Override
	public boolean validateEmail(String email) {
		if(email == null || email.isBlank()) {
			return false;
		}
		
		return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+$");
	}

	@Override
	public boolean validateMobile(String mobile) {
		if(mobile == null || mobile.isBlank()) {
			return false;
		}
		
		return mobile.matches("\\d{10}");
	}

	@Override
	public boolean validatePassword(String password) {
		
		if(password == null || password.isBlank()) {
			return false;
		}
		
		boolean isUppercase = false;
		boolean isLowercase = false;
		boolean isDigit = false;
		
		for(char ch : password.toCharArray()) {
			if(Character.isUpperCase(ch)) {
				isUppercase = true;
			}
			
			
			if(Character.isLowerCase(ch)) {
				isLowercase = true;
			}
			
			if(Character.isDigit(ch)) {
				isDigit = true;
			}
		}
		
		return isUppercase && isLowercase && isDigit;
	}

	@Override
	public boolean isAccountLocked(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLoginAttempts(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
