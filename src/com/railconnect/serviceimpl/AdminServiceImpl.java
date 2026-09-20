package com.railconnect.serviceimpl;
import java.util.regex.Pattern;

import java.util.List;

import com.railconnect.dao.AdminDao;
import com.railconnect.exception.DuplicateEmailException;
import com.railconnect.exception.DuplicateMobileException;
import com.railconnect.exception.DuplicateUsernameException;
import com.railconnect.exception.InvalidPasswordException;
import com.railconnect.exception.InvalidUserException;
import com.railconnect.model.Train;
import com.railconnect.model.User;
import com.railconnect.service.AdminService;

public class AdminServiceImpl implements AdminService {
	
	  private AdminDao adminDao;
	  private static final Pattern EMAIL_PATTERN = Pattern.compile(
	            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
	    );

	    private static final Pattern MOBILE_PATTERN = Pattern.compile(
	            "^[0-9]{10}$"
	    );

	    public AdminServiceImpl(AdminDao adminDao) {
	        this.adminDao = adminDao;
	    }

	    @Override
	    public void addUser(User user) {

	        // 1. Check whether User object is null
	        if (user == null) {
	            throw new InvalidUserException(
	                    "User object cannot be null"
	            );
	        }

	        // 2. Validate username
	        if (!validateUsername(user.getUsername())) {
	            throw new InvalidUserException(
	                    "Invalid username. Username is required, must not be blank, and must have at least 3 characters"
	            );
	        }

	        // 3. Validate email
	        if (!validateEmail(user.getEmail())) {
	            throw new InvalidUserException(
	                    "Invalid email"
	            );
	        }

	        // 4. Validate mobile number
	        if (!validateMobile(user.getMobile())) {
	            throw new InvalidUserException(
	                    "Invalid mobile number"
	            );
	        }

	        // 5. Validate password
	        if (!validatePassword(user.getPassword())) {
	            throw new InvalidPasswordException(
	                    "Invalid password. Password must contain at least 8 characters, one uppercase letter, one lowercase letter and one digit"
	            );
	        }

	        // 6. Check whether User ID already exists
	        if (adminDao.findbyUserId(user.getUserId())) {
	            throw new InvalidUserException(
	                    "Invalid user id. Proper user id required"
	            );
	        }

	        // 7. Check whether username already exists
	        if (adminDao.findByUsername(user.getUsername()) != null) {
	            throw new DuplicateUsernameException(
	                    "Username '" + user.getUsername() + "' is already taken."
	            );
	        }

	        // 8. Check whether email already exists
	        if (adminDao.findByEmail(user.getEmail()) != null) {
	            throw new DuplicateEmailException(
	                    "Email '" + user.getEmail() + "' is already registered."
	            );
	        }

	        // 9. Check whether mobile already exists
	        if (adminDao.findByMobile(user.getMobile()) != null) {
	            throw new DuplicateMobileException(
	                    "Mobile number '" + user.getMobile() + "' is already registered."
	            );
	        }

	        // 10. Generate User ID
	        int generateId = adminDao.generateUniqueUserId();

	        user.setUserId(generateId);

	        // 11. Set default values
	        user.setAccountLocked(false);
	        user.setLoginAttempts(0);

	        // 12. Save user
	        adminDao.saveUser(user);
	    }

    @Override
    public boolean validateUsername(String username) {

        if (username == null) {
            return false;
        }

        String trimmed = username.trim();

        return trimmed.length() >= 3;
    }
    @Override
    public boolean validateEmail(String email) {

        if (email == null) {
            return false;
        }

        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }
    @Override
    public boolean validateMobile(String mobile) {

        if (mobile == null) {
            return false;
        }

        return MOBILE_PATTERN.matcher(mobile.trim()).matches();
    }

    public boolean validatePassword(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean isUppercase = false;
        boolean isLowercase = false;
        boolean isDigit = false;

        for (char ch : password.toCharArray()) {

            if (Character.isUpperCase(ch)) {
                isUppercase = true;
            }

            if (Character.isLowerCase(ch)) {
                isLowercase = true;
            }

            if (Character.isDigit(ch)) {
                isDigit = true;
            }
        }

        return isUppercase && isLowercase && isDigit;
    }
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
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
