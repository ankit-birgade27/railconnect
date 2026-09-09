package com.railconnect.serviceimpl;

import java.util.List;

import com.railconnect.controller.dao.UserDao;
import com.railconnect.controller.dao.UserDaoImpl;
import com.railconnect.exception.DuplicateEmailException;
import com.railconnect.exception.DuplicateMobileException;
import com.railconnect.exception.DuplicateUsernameException;
import com.railconnect.exception.InvalidPasswordException;
import com.railconnect.exception.InvalidUserException;
import com.railconnect.model.Passenger;
import com.railconnect.model.User;
import com.railconnect.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao dao;

    public UserServiceImpl() {
        this.dao = new UserDaoImpl();
    }

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void registerUser(User user) {
        // 1. Check whether User object is null
        if (user == null) {
            throw new InvalidUserException("User object cannot be null.");
        }

        // 2. Validate username
        if (!validateUsername(user.getUsername())) {
            throw new InvalidUserException("Invalid username: Username is required, must not be blank, and must have at least 3 characters.");
        }

        // 3. Validate email
        if (!validateEmail(user.getEmail())) {
            throw new InvalidUserException("Invalid email: Email is required and must follow a valid format (e.g., user@example.com).");
        }

        // 4. Validate mobile
        if (!validateMobile(user.getMobile())) {
            throw new InvalidUserException("Invalid mobile: Mobile number is required and must be a valid 10-digit number.");
        }

        // 5. Validate password
        if (!validatePassword(user.getPassword())) {
            throw new InvalidPasswordException("Invalid password: Password is required, must not be blank, and must be at least 6 characters long.");
        }

        // 6. Check whether username already exists
        if (dao.findByUsername(user.getUsername()) != null) {
            throw new DuplicateUsernameException("Username '" + user.getUsername() + "' is already taken.");
        }

        // 7. Check whether email already exists
        if (dao.findByEmail(user.getEmail()) != null) {
            throw new DuplicateEmailException("Email '" + user.getEmail() + "' is already registered.");
        }

        // 8. Check whether mobile already exists
        if (dao.findByMobile(user.getMobile()) != null) {
            throw new DuplicateMobileException("Mobile number '" + user.getMobile() + "' is already registered.");
        }

        // 9. Generate a unique user ID
        int uniqueId = dao.generateUniqueUserId();
        user.setUserId(uniqueId);

        // 10. Set default account values
        user.setRole("PASSENGER");
        user.setAccountLocked(false);
        user.setLoginAttempts(0);

        // 11. Save the user using DAO
        dao.saveUser(user);
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
        String trimmed = email.trim();
        if (trimmed.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return trimmed.matches(emailRegex);
    }

    public boolean validateMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        String trimmed = mobile.trim();
        return trimmed.matches("^[0-9]{10}$");
    }

    public boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }
        return password.trim().length() >= 6;
    }

    @Override
    public User viewProfile(int userId) {
        return dao.findById(userId);
    }

    @Override
    public void updateProfile(User user) {
        // TODO Auto-generated method stub
    }

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

    @Override
    public String welcome(String msg) {
        String newmsg = dao.getnewMessage(msg);
        if (newmsg != null && newmsg.length() > 5) {
            return newmsg;
        }
        return null;
    }
}
