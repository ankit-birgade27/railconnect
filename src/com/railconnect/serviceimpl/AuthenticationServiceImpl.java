package com.railconnect.serviceimpl;

import com.railconnect.model.User;
import com.railconnect.service.AuthenticationService;

public class AuthenticationServiceImpl  implements AuthenticationService{

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLoginAttempts(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
