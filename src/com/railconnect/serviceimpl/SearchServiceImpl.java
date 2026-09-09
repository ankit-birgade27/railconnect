package com.railconnect.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import com.railconnect.dao.SearchDao;
import com.railconnect.model.Train;
import com.railconnect.service.SearchService;

public class SearchServiceImpl implements  SearchService {
	
	   private SearchDao searchDao;

	    public SearchServiceImpl(SearchDao searchDao) {
	        this.searchDao = searchDao;
	    }

	@Override
	public List<Train> searchTrains(String source, String destination, LocalDate journeyDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Train> getTrainsBySource(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Train> getTrainsByDestination(String destination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Train getTrainDetails(int trainId) {
		// TODO Auto-generated method stub
		return null;
	}

}
