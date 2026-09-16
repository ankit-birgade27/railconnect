package com.railconnect.service;

import java.util.List;
import com.railconnect.exception.*;

import com.railconnect.model.Train;

public interface TrainService {

    void addTrain(Train train);

    Train getTrainById(int trainId);

    List<Train> getAllTrains();

    void updateTrain(Train train)
    throws InvalidTrainException,DuplicateTrainNumberException,
    TrainNotFoundException,RouteNotFoundException;

    void deleteTrain(int trainId);

    boolean trainExists(int trainId);
}
