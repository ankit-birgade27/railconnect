package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;


import com.railconnect.model.Fare;
import com.railconnect.model.Train;
import com.railconnect.storage.DataStore;

public class FareDao {

	
	   private List<Fare> fares = new ArrayList<>();
	   
	    public Train findTrainById(int trainId) {

	        for (Train train : DataStore.getTrains()) {

	            if (train.getTrainId() == trainId) {
	                return train;
	            }
	        }

	        return null;
	    }
	   
	   public Fare findByTrainId(int trainId) {

		    for (Fare fare : fares) {

		        if (fare.getTrain() != null &&
		            fare.getTrain().getTrainId() == trainId) {

		            return fare;
		        }
		    }

		    return null;
		}
}
