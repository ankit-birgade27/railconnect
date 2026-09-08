package com.railconnect.service;

import java.util.List;

import com.railconnect.model.Train;

public interface TrainService {

    void addTrain(Train train);

    Train getTrainById(int trainId);

    List<Train> getAllTrains();

    void updateTrain(Train train);

    void deleteTrain(int trainId);

    boolean trainExists(int trainId);
}
