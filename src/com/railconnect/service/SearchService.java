package com.railconnect.service;

import java.time.LocalDate;
import java.util.List;

import com.railconnect.exception.InvalidStationException;
import com.railconnect.model.Train;

public interface SearchService {

    List<Train> searchTrains(String source,
                             String destination,
                             LocalDate journeyDate);

    List<Train> getTrainsBySource(String source)throws InvalidStationException;

    List<Train> getTrainsByDestination(String destination);

    Train getTrainDetails(int trainId);
}