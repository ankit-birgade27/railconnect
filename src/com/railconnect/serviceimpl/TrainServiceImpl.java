package com.railconnect.serviceimpl;

import java.util.List;

import com.railconnect.dao.TrainDao;
import com.railconnect.exception.InvalidTrainIdException;
import com.railconnect.exception.TrainNotFoundException;
import com.railconnect.model.Train;
import com.railconnect.service.TrainService;

public class TrainServiceImpl  implements TrainService{
	

	     private TrainDao trainDao;

	    public TrainServiceImpl(TrainDao trainDao) {
	        this.trainDao = trainDao;
	    }

	@Override
	public void addTrain(Train train) {
		// TODO Auto-generated method stub
		
	}
		
		@Override
		// TODO Auto-generated method stub
		public Train getTrainById(int trainId)
		        throws InvalidTrainIdException, TrainNotFoundException {

		    // 1. Validate Train ID
		    if (trainId <= 0) {
		        throw new InvalidTrainIdException("Invalid Train ID");
		    }

		    // 2. Find train using DAO
		    Train train = trainDao.findById(trainId);

		    // 3. Check whether train exists
		    if (train == null) {
		        throw new TrainNotFoundException("Train not found with ID: " + trainId);
		    }

		    // 4. Return train
		    return train;
		}
		
		
	@Override
	public List<Train> getAllTrains() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTrain(Train train) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTrain(int trainId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean trainExists(int trainId) {
		// TODO Auto-generated method stub
		return false;
	}

}