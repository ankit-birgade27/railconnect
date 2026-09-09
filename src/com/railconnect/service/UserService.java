package com.railconnect.service;

import java.util.List;

import com.railconnect.model.Passenger;
import com.railconnect.model.User;

public interface UserService {

    String welcome(String msg);

    void registerUser(User user);

    boolean validateUsername(String username);

    boolean validateEmail(String email);

    User viewProfile(int userId);

    void updateProfile(User user);

    void changePassword(int userId,
                        String oldPassword,
                        String newPassword);

    void forgotPassword(String email);

    void addPassenger(Passenger passenger);

    void updatePassenger(Passenger passenger);

    void deletePassenger(int passengerId);

    List<Passenger> getPassengers(int userId);

    List<String> getBookingHistory(int userId);
}