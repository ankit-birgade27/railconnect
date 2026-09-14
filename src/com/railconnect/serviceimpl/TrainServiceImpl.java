package com.railconnect.serviceimpl;

import java.util.List;
import com.railconnect.exception.*;

import com.railconnect.dao.TrainDao;
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
	public Train getTrainById(int trainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Train> getAllTrains() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTrain(Train train) 
	throws InvalidTrainException, DuplicateTrainNumberException, TrainNotFoundException,
	RouteNotFoundException{
		
		if(train==null) {
			throw new InvalidTrainException("train cannot be null");
		}
		
		if (train.getTrainId() <= 0) {
		    throw new InvalidTrainException("Train ID must be positive");
		}
		
		Train existingTrain = trainDao.findById(train.getTrainId());
		
		if(existingTrain==null) {
			throw new TrainNotFoundException("Train not found with this Id");
		}
		
		if (train.getTrainName() == null ||
			    train.getTrainName().trim().isEmpty()) {
			throw new InvalidTrainException("Train name cannot be empty");
			}
		
		if (train.getTrainNumber() == null ||
			    train.getTrainNumber().trim().isEmpty()) {
			throw new InvalidTrainException("Train number cannot be empty");
			}
		
		Train trainWithSameNumber =
		        trainDao.findByTrainNumber(train.getTrainNumber());
		
		if (trainWithSameNumber != null &&
			    trainWithSameNumber.getTrainId() != train.getTrainId()) {
			throw new DuplicateTrainNumberException("Train number already exists");
			}
		
		if (train.getRoute() == null) {
			throw new RouteNotFoundException("Route cannot be empty");
			}
		
		if (train.getDepartureTime() == null ||
			    train.getDepartureTime().trim().isEmpty()) {
			throw new InvalidTrainException("Departure time cannot be empty");
			}
		
		if (train.getArrivalTime() == null ||
			    train.getArrivalTime().trim().isEmpty()) {
			throw new InvalidTrainException("Arrival time cannot be empty");
			}
		
		trainDao.updateTrain(train);
	
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
