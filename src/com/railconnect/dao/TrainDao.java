package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.Train;
import com.railconnect.storage.DataStore;

public class TrainDao {
	
	public synchronized Train findById(int trainId) {
		List<Train> trains = DataStore.getTrains();
		for(Train train: trains) {
			if(train != null && train.getTrainId() == trainId) {
				return train;
			}
		}
		
		return null;
	}

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

	public synchronized void clear() {
		DataStore.getTrains().clear();
	}

	public synchronized boolean existsById(int trainId) {
		return findById(trainId) != null;
	}

	public synchronized List<Train> findAll() {
		return new ArrayList<>(DataStore.getTrains());
	}

}

