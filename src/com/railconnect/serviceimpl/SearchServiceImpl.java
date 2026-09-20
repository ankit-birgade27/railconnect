package com.railconnect.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import com.railconnect.dao.SearchDao;
import com.railconnect.exception.InvalidStationException;
import com.railconnect.exception.TrainNotFoundException;
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
	public List<Train> getTrainsBySource(String source)
			throws InvalidStationException {

    
        // 1. Check null or empty
        if (source == null || source.trim().isEmpty())
        {
            throw new InvalidStationException(
                    "Source station cannot be null or empty.");
        }

        // 2. Validate source station
        source = source.trim();

        if (!source.matches("[a-zA-Z ]+"))
        {
            throw new InvalidStationException(
                    "Invalid source station.");
        }

        // 3. Retrieve trains from DAO
        List<Train> trains = searchDao.findBySource(source);

        // 4. Check whether trains are found
        if (trains == null || trains.isEmpty())
        {
            throw new TrainNotFoundException(
                    "No trains found from source: " + source);
        }

        // 5. Return matching trains
        return trains;
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
