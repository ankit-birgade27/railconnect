package com.railconnect.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.Route;
import com.railconnect.model.Station;
import com.railconnect.model.Train;
import com.railconnect.storage.DataStore;

public class SearchDao {

    /**
     * Retrieves all trains available in the data store.
     */
    public synchronized List<Train> getAllTrains() {
        return new ArrayList<>(DataStore.getTrains());
    }

    /**
     * Finds the first train matching the route with source before destination.
     */
    public synchronized Train findByRoute(String source, String destination) {
        if (source == null || destination == null) {
            return null;
        }
        for (Train train : DataStore.getTrains()) {
            if (train != null && train.getRoute() != null) {
                int srcIdx = findStationIndex(train.getRoute(), source);
                int destIdx = findStationIndex(train.getRoute(), destination);
                if (srcIdx != -1 && destIdx != -1 && srcIdx < destIdx) {
                    return train;
                }
            }
        }
        return null;
    }

    /**
     * Retrieves trains available for a given journey date.
     */
    public synchronized List<Train> findByJourneyDate(LocalDate journeyDate) {
        if (journeyDate == null) {
            return new ArrayList<>();
        }
        // In the project's data model, all registered trains are active for valid journey dates
        return new ArrayList<>(DataStore.getTrains());
    }

    /**
     * Retrieves a train by its ID.
     */
    public synchronized Train findById(int trainId) {
        for (Train train : DataStore.getTrains()) {
            if (train != null && train.getTrainId() == trainId) {
                return train;
            }
        }
        return null;
    }

    /**
     * Checks whether a station query matches any station in any registered train route.
     */
    public synchronized boolean isValidStation(String query) {
        if (query == null || query.trim().isEmpty()) {
            return false;
        }
        String q = query.trim();
        for (Train train : DataStore.getTrains()) {
            if (train != null && train.getRoute() != null && train.getRoute().getStations() != null) {
                for (Station station : train.getRoute().getStations()) {
                    if (station != null && matchesStation(station, q)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if a single station matches the query by code, name, or city.
     */
    public boolean matchesStation(Station station, String query) {
        if (station == null || query == null) {
            return false;
        }
        String q = query.trim();
        if (station.getStationCode() != null && station.getStationCode().trim().equalsIgnoreCase(q)) {
            return true;
        }
        if (station.getStationName() != null && station.getStationName().trim().equalsIgnoreCase(q)) {
            return true;
        }
        if (station.getCity() != null && station.getCity().trim().equalsIgnoreCase(q)) {
            return true;
        }
        return false;
    }

    /**
     * Finds the index of a station in a route's station list.
     * Returns -1 if not found.
     */
    public int findStationIndex(Route route, String stationQuery) {
        if (route == null || route.getStations() == null || stationQuery == null) {
            return -1;
        }
        List<Station> stations = route.getStations();
        for (int i = 0; i < stations.size(); i++) {
            Station s = stations.get(i);
            if (matchesStation(s, stationQuery)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Saves or updates a train in the data store.
     */
    public synchronized void saveTrain(Train train) {
        if (train == null) {
            return;
        }
        List<Train> trains = DataStore.getTrains();
        for (int i = 0; i < trains.size(); i++) {
            Train existing = trains.get(i);
            if (existing != null && existing.getTrainId() == train.getTrainId()) {
                trains.set(i, train);
                return;
            }
        }
        trains.add(train);
    }

    /**
     * Clears all trains from data store (used for test teardown).
     */
    public synchronized void clear() {
        DataStore.getTrains().clear();
    }
}
