package com.railconnect.serviceimpl;

import java.util.List;

import com.railconnect.dao.UserDao;
import com.railconnect.exception.DuplicateEmailException;
import com.railconnect.exception.DuplicateMobileException;
import com.railconnect.exception.DuplicateUsernameException;
import com.railconnect.exception.InvalidPasswordException;
import com.railconnect.exception.InvalidUserException;
import com.railconnect.model.Passenger;
import com.railconnect.model.User;
import com.railconnect.service.AuthenticationService;
import com.railconnect.service.UserService;

public class UserServiceImpl implements UserService {

<<<<<<< Updated upstream

    private UserDao userDao;
    private AuthenticationService authenticationService;
    
    
=======
    private UserDao userDao;
    private AuthenticationService authenticationService;

//    private static final Pattern EMAIL_PATTERN = Pattern.compile(
//            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
//    );
//
//    private static final Pattern MOBILE_PATTERN = Pattern.compile(
//            "^[0-9]{10}$"
//    );
>>>>>>> Stashed changes

    public UserServiceImpl(UserDao userDao, AuthenticationService authenticationService) {
        this.userDao = userDao;
        this.authenticationService = authenticationService;
    }
	
	
	@Override
	public void registerUser(User user) {
		if(user == null) {
			throw new InvalidUserException("User cannot be null");
		}
		if (!authenticationService.validateUsername(user.getUsername())) {
		    throw new InvalidUserException("Invalid username");
		}

<<<<<<< Updated upstream
		if (!authenticationService.validateEmail(user.getEmail())) {
		    throw new InvalidUserException("Invalid email");
		}

		if (!authenticationService.validateMobile(user.getMobile())) {
		    throw new InvalidUserException("Invalid mobile number");
		}

		if (!authenticationService.validatePassword(user.getPassword())) {
		    throw new InvalidPasswordException("Invalid password");
		}
		
		if(userDao.findByUsername(user.getUsername()) != null) {
			throw new DuplicateUsernameException("Username already exist");
		}
		
		if(userDao.findByEmail(user.getEmail()) != null) {
			throw new DuplicateEmailException("Email already exist");
		}
		
		if(userDao.findByMobile(user.getMobile()) != null) {
			throw new DuplicateMobileException("Mobile number already exist");
		}
		
		
		
		int maxId = 0;
		
		for(User existingUser: userDao.findAll()) {
			if(existingUser.getUserId() > maxId) {
				maxId = existingUser.getUserId();
			}
		}
		
		int newId = maxId+1;
		user.setUserId(newId);
		user.setRole("PASSENGER");
		user.setAccountLocked(false);
		user.setLoginAttempts(0);
		
		userDao.save(user);
		
	}


	@Override
	public User viewProfile(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
=======
//    public UserServiceImpl(UserDao dao) {
//        this.dao = dao;
//    }

//    @Override
//    public boolean validateUsername(String username) {
//        if (username == null) {
//            return false;
//        }
//        String trimmed = username.trim();
//        return trimmed.length() >= 3;
//    }

//    @Override
//    public boolean validateEmail(String email) {
//        if (email == null) {
//            return false;
//        }
//        return EMAIL_PATTERN.matcher(email.trim()).matches();
//    }
//
//    public boolean validateMobile(String mobile) {
//        if (mobile == null) {
//            return false;
//        }
//        return MOBILE_PATTERN.matcher(mobile.trim()).matches();
//    }
//
//    public boolean validatePassword(String password) {
//        if (password == null) {
//            return false;
//        }
//        return password.length() >= 6;
//    }
>>>>>>> Stashed changes

	@Override
	public void updateProfile(User user) {
		// TODO Auto-generated method stub
		
	}

<<<<<<< Updated upstream
	@Override
	public void changePassword(int userId, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forgotPassword(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePassenger(int passengerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Passenger> getPassengers(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getBookingHistory(int userId) {
		// TODO Auto-generated method stub
		return null;
	}


=======
        // Step 2: Validate username
        if (!authenticationService.validateUsername(user.getUsername())) {
            throw new InvalidUserException("Invalid username. Username is required, must not be blank, and must have at least 3 characters.");
        }

        // Step 3: Validate email
        if (!authenticationService.validateEmail(user.getEmail())) {
            throw new InvalidUserException("Invalid email format. Please provide a valid email address (e.g. user@example.com).");
        }

        // Step 4: Validate mobile
        if (!authenticationService.validateMobile(user.getMobile())) {
            throw new InvalidUserException("Invalid mobile number. Mobile must contain exactly 10 digits.");
        }

        // Step 5: Validate password
        if (!authenticationService.validatePassword(user.getPassword())) {
        	throw new InvalidPasswordException(
        		    "Invalid password. Password must be at least 8 characters "
        		    + "and contain at least one uppercase letter, one lowercase letter, and one digit."
        		);
        }

        // Step 6: Check whether username already exists
        if (userDao.findByUsername(user.getUsername()) != null) {
            throw new DuplicateUsernameException("Username '" + user.getUsername() + "' is already taken.");
        }

        // Step 7: Check whether email already exists
        if (userDao.findByEmail(user.getEmail()) != null) {
            throw new DuplicateEmailException("Email '" + user.getEmail() + "' is already registered.");
        }

        // Step 8: Check whether mobile already exists
        if (userDao.findByMobile(user.getMobile()) != null) {
            throw new DuplicateMobileException("Mobile number '" + user.getMobile() + "' is already registered.");
        }

        // Step 9: Generate a unique user ID
        int generatedId = userDao.generateUniqueUserId();
        user.setUserId(generatedId);

        // Step 10: Set default account values
        user.setRole("PASSENGER");
        user.setAccountLocked(false);
        user.setLoginAttempts(0);

        // Step 11: Save the user using DAO
        userDao.saveUser(user);
    }

    @Override
    public User viewProfile(int userId) {
        // Handled by Student 2
        return null;
    }

    @Override
    public void updateProfile(User user) {
        // Handled by Student 2
    }

    @Override
    public void changePassword(int userId, String oldPassword, String newPassword) {
        // Handled by Student 3
    }

    @Override
    public void forgotPassword(String email) {
        // Handled by Student 3
    }

    @Override
    public void addPassenger(Passenger passenger) {
        // Handled by Student 4
    }

    @Override
    public void updatePassenger(Passenger passenger) {
        // Handled by Student 4
    }

    @Override
    public void deletePassenger(int passengerId) {
        // Handled by Student 4
    }

    @Override
    public List<Passenger> getPassengers(int userId) {
        // Handled by Student 5
        return null;
    }

    @Override
    public List<String> getBookingHistory(int userId) {
        // Handled by Student 5
        return null;
    }

//    @Override
//    public String welcome(String msg) {
//        String newmsg = dao.getnewMessage(msg);
//        if (newmsg != null && newmsg.length() > 5) {
//            return newmsg;
//        }
//        return null;
//    }
>>>>>>> Stashed changes
}
