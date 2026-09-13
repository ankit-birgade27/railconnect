package com.railconnect.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.railconnect.model.Train;
import com.railconnect.storage.DataStore;

public class TrainDao {

    private static final AtomicInteger idGenerator = new AtomicInteger(101);

    public synchronized void saveTrain(Train train) {
        if (train == null) {
            return;
        }
        List<Train> trains = DataStore.getTrains();
        for (int i = 0; i < trains.size(); i++) {
            if (trains.get(i).getTrainId() == train.getTrainId()) {
                trains.set(i, train);
                return;
            }
        }
        trains.add(train);
    }

    public synchronized Train findByTrainNumber(String trainNumber) {
        if (trainNumber == null) {
            return null;
        }
        String trimmed = trainNumber.trim();
        List<Train> trains = DataStore.getTrains();
        for (Train train : trains) {
            if (train != null && train.getTrainNumber() != null &&
                    train.getTrainNumber().trim().equalsIgnoreCase(trimmed)) {
                return train;
            }
        }
        return null;
    }

    public synchronized Train findById(int trainId) {
        List<Train> trains = DataStore.getTrains();
        for (Train train : trains) {
            if (train != null && train.getTrainId() == trainId) {
                return train;
            }
        }
        return null;
    }

    public synchronized List<Train> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(DataStore.getTrains()));
    }

    public synchronized int generateUniqueTrainId() {
        int maxId = 100;
        for (Train t : DataStore.getTrains()) {
            if (t != null && t.getTrainId() > maxId) {
                maxId = t.getTrainId();
            }
        }
        int nextId = idGenerator.get();
        if (nextId <= maxId) {
            nextId = maxId + 1;
            idGenerator.set(nextId + 1);
            return nextId;
        }
        return idGenerator.getAndIncrement();
    }

    public synchronized void clear() {
        DataStore.getTrains().clear();
        idGenerator.set(101);
    }
}
