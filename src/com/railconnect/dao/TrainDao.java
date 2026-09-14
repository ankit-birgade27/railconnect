package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.Train;

public class TrainDao {
	
	
	   private List<Train> trains = new ArrayList<>();

	   public Train findById(int trainId) {
		   for (Train train : trains) {
			if(train.getTrainId()==trainId) {
				return train;
			}
		}
		   return null;
	   }
	   
	   public Train findByTrainNumber(String trainNumber) {
		   for (Train train : trains) {
			if(train.getTrainNumber().equals(trainNumber)) {
				return train;
			}
		}
		   return null;
	   }
	   
	  public  void updateTrain(Train train) {
		  for (int i = 0; i < trains.size(); i++) {
			  if(trains.get(i).getTrainId()==train.getTrainId()) {
				  trains.set(i, train);
				  return;
			  }
		}
	  }
}
