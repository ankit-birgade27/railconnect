package com.railconnect.serviceimpl;

import java.util.List;
import java.util.regex.Pattern;

import com.railconnect.dao.AdminDao;
import com.railconnect.exception.DuplicateEmailException;
import com.railconnect.exception.DuplicateMobileException;
import com.railconnect.exception.DuplicateUsernameException;
import com.railconnect.exception.InvalidUserException;
import com.railconnect.exception.UserNotFoundException;
import com.railconnect.model.Train;
import com.railconnect.model.User;
import com.railconnect.service.AdminService;

public class AdminServiceImpl implements AdminService {
	
	  private AdminDao adminDao;

	    public AdminServiceImpl(AdminDao adminDao) {
	        this.adminDao = adminDao;
	    }

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		 // 1. Check whether User object is null
        if (user == null) {
            throw new InvalidUserException("User cannot be null");
        }

        // 2. Validate User ID
        if (user.getUserId() <= 0) {
            throw new InvalidUserException("Invalid User ID");
        }

        // 3. Check whether user exists
        User existingUser = adminDao.findById(user.getUserId());

        if (existingUser == null) {
            throw new UserNotFoundException("User not found");
        }

        // 4. Validate required fields

        if (user.getUsername() == null ||
            user.getUsername().trim().isEmpty()) {

            throw new InvalidUserException("Username is required");
        }

        if (user.getEmail() == null ||
            user.getEmail().trim().isEmpty()) {

            throw new InvalidUserException("Email is required");
        }

        if (user.getMobile() == null ||
            user.getMobile().trim().isEmpty()) {

            throw new InvalidUserException("Mobile is required");
        }

        // Validate username
        if (!Pattern.matches("^[a-zA-Z0-9_]{3,20}$",
                user.getUsername())) {

            throw new InvalidUserException("Invalid username");
        }

        // Validate email
        if (!Pattern.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
                user.getEmail())) {

            throw new InvalidUserException("Invalid email");
        }

        // Validate mobile
        if (!Pattern.matches("^[6-9][0-9]{9}$",
                user.getMobile())) {

            throw new InvalidUserException("Invalid mobile number");
        }

        // 5. Check duplicate username
        User usernameUser =
                adminDao.findByUsername(user.getUsername());

        if (usernameUser != null &&
            usernameUser.getUserId() != user.getUserId()) {

            throw new DuplicateUsernameException(
                    "Username already exists");
        }

        // Check duplicate email
        User emailUser =
                adminDao.findByEmail(user.getEmail());

        if (emailUser != null &&
            emailUser.getUserId() != user.getUserId()) {

            throw new DuplicateEmailException(
                    "Email already exists");
        }

        // Check duplicate mobile
        User mobileUser =
                adminDao.findByMobile(user.getMobile());

        if (mobileUser != null &&
            mobileUser.getUserId() != user.getUserId()) {

            throw new DuplicateMobileException(
                    "Mobile already exists");
        }

        // 6 & 7. Update and save user
        adminDao.updateUser(user);
    }

	
	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTrain(Train train) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTrain(Train train) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTrain(int trainId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Train> getAllTrains() {
		// TODO Auto-generated method stub
		return null;
	}

	}