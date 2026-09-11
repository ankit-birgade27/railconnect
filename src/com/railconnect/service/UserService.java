package com.railconnect.service;

import java.util.List;
import com.railconnect.exception.InvalidPassengerException;
import com.railconnect.exception.PassengerNotFoundException;
import com.railconnect.exception.UnauthorizedAccessException;
import com.railconnect.exception.UserNotFoundException;

import com.railconnect.model.Passenger;
import com.railconnect.model.User;

public interface UserService {

	

    void registerUser(User user);

    User viewProfile(int userId);

    void updateProfile(User user);

    void changePassword(int userId,
                        String oldPassword,
                        String newPassword);

    void forgotPassword(String email);

    void addPassenger(Passenger passenger)
    		throws UserNotFoundException, InvalidPassengerException;

    void updatePassenger(Passenger passenger)
    		throws PassengerNotFoundException,
            InvalidPassengerException,
            UnauthorizedAccessException;

    void deletePassenger(int passengerId)
    		throws PassengerNotFoundException,
            UnauthorizedAccessException;

    List<Passenger> getPassengers(int userId);

    List<String> getBookingHistory(int userId);
}