package com.railconnect.dao;

import java.util.List;

import com.railconnect.model.Train;
import com.railconnect.storage.DataStore;

public class TrainDao {
	
	public Train findById(int trainId) {
		List<Train> trains = DataStore.getTrains();
		for(Train train: trains) {
			if(train.getTrainId() == trainId) {
				return train;
			}
		}
		
		return null;
	}

}
