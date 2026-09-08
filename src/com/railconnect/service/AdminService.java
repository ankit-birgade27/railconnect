package com.railconnect.service;

import java.util.List;

import com.railconnect.model.Train;
import com.railconnect.model.User;

public interface AdminService {

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(int userId);

    User getUserById(int userId);

    List<User> getAllUsers();

    void addTrain(Train train);

    void updateTrain(Train train);

    void deleteTrain(int trainId);

    List<Train> getAllTrains();
}