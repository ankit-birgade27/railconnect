package com.railconnect.serviceimpl;

import java.util.List;

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
