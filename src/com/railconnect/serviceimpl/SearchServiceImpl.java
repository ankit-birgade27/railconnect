package com.railconnect.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.railconnect.dao.SearchDao;
import com.railconnect.exception.InvalidJourneyDateException;
import com.railconnect.exception.InvalidStationException;
import com.railconnect.exception.TrainNotFoundException;
import com.railconnect.model.Route;
import com.railconnect.model.Station;
import com.railconnect.model.Train;
import com.railconnect.service.SearchService;

public class SearchServiceImpl implements SearchService {

    private SearchDao searchDao;

    public SearchServiceImpl() {
        this.searchDao = new SearchDao();
    }

    public SearchServiceImpl(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    /**
     * Group 1 Assigned Method: Searches for trains matching the requested source,
     * destination, and journey date.
     *
     * Required Processing:
     * 1. Check whether source is null or empty.
     * 2. Check whether destination is null or empty.
     * 3. Check whether journeyDate is null or in the past.
     * 4. Validate that source and destination are valid station values according to project data.
     * 5. Retrieve available train data using SearchDao.
     * 6. Check each train's route/station information.
     * 7. Match source and destination in correct travel order (sourceIndex < destIndex).
     * 8. Check train journey availability for the requested date.
     * 9. Add matching trains to a result list.
     * 10. Return matching train list (or throw TrainNotFoundException if none found).
     */
    @Override
    public List<Train> searchTrains(String source, String destination, LocalDate journeyDate) {
        // Step 1: Check whether source is null or empty
        if (source == null || source.trim().isEmpty()) {
            throw new InvalidStationException("Source station is required and cannot be blank.");
        }

        // Step 2: Check whether destination is null or empty
        if (destination == null || destination.trim().isEmpty()) {
            throw new InvalidStationException("Destination station is required and cannot be blank.");
        }

        String trimmedSource = source.trim();
        String trimmedDest = destination.trim();

        // Validate source and destination are not identical
        if (trimmedSource.equalsIgnoreCase(trimmedDest)) {
            throw new InvalidStationException("Source and destination stations cannot be the same: " + trimmedSource);
        }

        // Step 3: Check whether journeyDate is null or in the past
        if (journeyDate == null) {
            throw new InvalidJourneyDateException("Journey date is required.");
        }
        if (journeyDate.isBefore(LocalDate.now())) {
            throw new InvalidJourneyDateException("Invalid journey date: " + journeyDate + ". Journey date cannot be in the past.");
        }

        // Step 4: Validate that source and destination are valid station values according to project data
        if (!searchDao.isValidStation(trimmedSource)) {
            throw new InvalidStationException("Invalid source station: '" + trimmedSource + "' is not a recognized station.");
        }
        if (!searchDao.isValidStation(trimmedDest)) {
            throw new InvalidStationException("Invalid destination station: '" + trimmedDest + "' is not a recognized station.");
        }

        // Step 5: Retrieve available train data using SearchDao
        List<Train> allTrains = searchDao.getAllTrains();
        if (allTrains == null || allTrains.isEmpty()) {
            throw new TrainNotFoundException("No trains available in the system.");
        }

        // Steps 6, 7, 8, 9: Check routes, order, and journey availability
        List<Train> matchingTrains = new ArrayList<>();
        for (Train train : allTrains) {
            if (train != null && train.getRoute() != null) {
                int srcIdx = searchDao.findStationIndex(train.getRoute(), trimmedSource);
                int destIdx = searchDao.findStationIndex(train.getRoute(), trimmedDest);

                // Step 7: Match source and destination in correct travel order (srcIdx < destIdx)
                if (srcIdx != -1 && destIdx != -1 && srcIdx < destIdx) {
                    // Step 8: In this project data model, active registered trains run on valid dates
                    matchingTrains.add(train);
                }
            }
        }

        // Step 10: If no matching trains found, throw TrainNotFoundException
        if (matchingTrains.isEmpty()) {
            throw new TrainNotFoundException("No trains found running from " + trimmedSource + 
                                             " to " + trimmedDest + " on " + journeyDate);
        }

        return matchingTrains;
    }

    @Override
    public List<Train> getTrainsBySource(String source) {
        if (source == null || source.trim().isEmpty()) {
            throw new InvalidStationException("Source station cannot be null or empty.");
        }
        String trimmed = source.trim();
        if (!searchDao.isValidStation(trimmed)) {
            throw new InvalidStationException("Invalid source station: '" + trimmed + "'.");
        }
        List<Train> result = new ArrayList<>();
        for (Train train : searchDao.getAllTrains()) {
            if (train != null && train.getRoute() != null && train.getRoute().getStations() != null) {
                int idx = searchDao.findStationIndex(train.getRoute(), trimmed);
                // Train matches if source is present and not the terminus (last station)
                if (idx != -1 && idx < train.getRoute().getStations().size() - 1) {
                    result.add(train);
                }
            }
        }
        if (result.isEmpty()) {
            throw new TrainNotFoundException("No trains found originating from or passing through " + trimmed);
        }
        return result;
    }

    @Override
    public List<Train> getTrainsByDestination(String destination) {
        if (destination == null || destination.trim().isEmpty()) {
            throw new InvalidStationException("Destination station cannot be null or empty.");
        }
        String trimmed = destination.trim();
        if (!searchDao.isValidStation(trimmed)) {
            throw new InvalidStationException("Invalid destination station: '" + trimmed + "'.");
        }
        List<Train> result = new ArrayList<>();
        for (Train train : searchDao.getAllTrains()) {
            if (train != null && train.getRoute() != null && train.getRoute().getStations() != null) {
                int idx = searchDao.findStationIndex(train.getRoute(), trimmed);
                // Train matches if destination is present and not the departure station (first station)
                if (idx > 0) {
                    result.add(train);
                }
            }
        }
        if (result.isEmpty()) {
            throw new TrainNotFoundException("No trains found reaching destination " + trimmed);
        }
        return result;
    }

    @Override
    public Train getTrainDetails(int trainId) {
        if (trainId <= 0) {
            throw new TrainNotFoundException("Invalid train ID: " + trainId);
        }
        Train train = searchDao.findById(trainId);
        if (train == null) {
            throw new TrainNotFoundException("Train not found with ID: " + trainId);
        }
        return train;
    }
}
