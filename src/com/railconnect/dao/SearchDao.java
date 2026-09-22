package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.Route;
import com.railconnect.model.Station;
import com.railconnect.model.Train;
import com.railconnect.storage.DataStore;

public class SearchDao {

	
	 private List<Train> trains = new ArrayList<>();
	 
	 public List<Train> getAllTrains()
	    {
	        return DataStore.getTrains();
	    }

	    public List<Train> findBySource(String source)
	    {
	        List<Train> result = new ArrayList<>();

	        List<Train> trains = DataStore.getTrains();

	        for (Train train : trains) {

	            Route route = train.getRoute();

	            if (route != null && route.getStations() != null) {

	                for (Station station : route.getStations()) {

	                    if (station != null &&
	                        station.getStationName() != null &&
	                        station.getStationName().equalsIgnoreCase(source)) {

	                        result.add(train);
	                        break;
	                    }

	                    if (station != null &&
	                        station.getStationCode() != null &&
	                        station.getStationCode().equalsIgnoreCase(source)) {

	                        result.add(train);
	                        break;
	                    }
	                }
	            }
	        }

	        return result;
	    }
	}

