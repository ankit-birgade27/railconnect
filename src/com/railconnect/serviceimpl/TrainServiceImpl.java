package com.railconnect.serviceimpl;

import java.util.ArrayList;

import java.util.List;

import java.util.regex.Pattern;

import com.railconnect.exception.*;


import com.railconnect.dao.TrainDao;
import com.railconnect.exception.DuplicateTrainNumberException;

import com.railconnect.exception.InvalidTrainException;
import com.railconnect.exception.TrainNotFoundException;

import com.railconnect.exception.RouteNotFoundException;
import com.railconnect.model.Coach;
import com.railconnect.model.Route;
import com.railconnect.model.Seat;
import com.railconnect.model.Train;
import com.railconnect.service.TrainService;

public class TrainServiceImpl  implements TrainService{
	    private TrainDao trainDao;

	    public TrainServiceImpl(TrainDao trainDao) {
	        this.trainDao = trainDao;
	    }

	@Override
	public Train getTrainById(int trainId) 
		// TODO Auto-generated method stub
		
		 throws InvalidTrainException, TrainNotFoundException {

			    // 1. Validate Train ID
			    if (trainId <= 0) {
			        throw new InvalidTrainException("Invalid Train ID");
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
		return trainDao.findAll();
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
		
		
	
	}

	@Override
	public void deleteTrain(int trainId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean trainExists(int trainId) {
		if(trainId <= 0) {
			throw new InvalidTrainException("Train ID must be positive.");
		}
		Train train = trainDao.findById(trainId);
		
		return train != null;
	}


    

    private static final Pattern TIME_PATTERN = Pattern.compile(
            "^([01]\\d|2[0-3]):[0-5]\\d(:[0-5]\\d)?$"
    );

    public TrainServiceImpl() {
        this.trainDao = new TrainDao();
    }

    @Override
    public void addTrain(Train train) {
        // Step 1: Check whether the Train object is null
        if (train == null) {
            throw new InvalidTrainException("Train object cannot be null.");
        }

        // Step 2: Validate the required train information (trainNumber, trainName, timings)
        if (train.getTrainNumber() == null || train.getTrainNumber().trim().isEmpty()) {
            throw new InvalidTrainException("Train number is required and cannot be blank.");
        }

        if (train.getTrainName() == null || train.getTrainName().trim().isEmpty()) {
            throw new InvalidTrainException("Train name is required and cannot be blank.");
        }

        // Step 3: Check whether the train ID is valid when IDs are supplied by the application
        if (train.getTrainId() < 0) {
            throw new InvalidTrainException("Train ID cannot be negative.");
        }
        if (train.getTrainId() > 0) {
            if (trainDao.findById(train.getTrainId()) != null) {
                throw new InvalidTrainException("Train with ID " + train.getTrainId() + " already exists.");
            }
        }

        // Step 4: Check whether the train number is provided and unique
        if (trainDao.findByTrainNumber(train.getTrainNumber()) != null) {
            throw new DuplicateTrainNumberException("Train with number '" + train.getTrainNumber() + "' already exists.");
        }

        // Step 5: Check whether the train name is provided
        if (train.getTrainName().trim().length() < 2) {
            throw new InvalidTrainException("Train name must be at least 2 characters long.");
        }

        // Step 6: Check whether the route exists or is valid
        Route route = train.getRoute();
        if (route == null) {
            throw new RouteNotFoundException("Route cannot be null. A valid route must be assigned to the train.");
        }
        if (route.getRouteId() == null || route.getRouteId().trim().isEmpty()) {
            throw new RouteNotFoundException("Route ID is required and cannot be blank.");
        }
        if (route.getRouteName() == null || route.getRouteName().trim().isEmpty()) {
            throw new RouteNotFoundException("Route name is required and cannot be blank.");
        }

        // Step 7: Validate departure and arrival time information
        if (train.getDepartureTime() == null || !TIME_PATTERN.matcher(train.getDepartureTime().trim()).matches()) {
            throw new InvalidTrainException("Invalid departure time '" + train.getDepartureTime() + "'. Expected format is HH:mm (e.g., 08:30).");
        }
        if (train.getArrivalTime() == null || !TIME_PATTERN.matcher(train.getArrivalTime().trim()).matches()) {
            throw new InvalidTrainException("Invalid arrival time '" + train.getArrivalTime() + "'. Expected format is HH:mm (e.g., 18:45).");
        }

        // Step 8: Validate the coach collection when coaches are supplied
        if (train.getCoaches() != null && !train.getCoaches().isEmpty()) {
            for (Coach coach : train.getCoaches()) {
                if (coach == null) {
                    throw new InvalidTrainException("Coach entry cannot be null.");
                }
                if (coach.getCoachNumber() == null || coach.getCoachNumber().trim().isEmpty()) {
                    throw new InvalidTrainException("Coach number cannot be null or blank.");
                }
                if (coach.getTotalSeats() <= 0) {
                    throw new InvalidTrainException("Total seats for coach '" + coach.getCoachNumber() + "' must be greater than 0.");
                }
            }
        }

        // Step 9: Set required default values according to project rules
        if (train.getTrainId() <= 0) {
            train.setTrainId(trainDao.generateUniqueTrainId());
        }
        if (train.getCoaches() == null) {
            train.setCoaches(new ArrayList<>());
        } else {
            for (Coach coach : train.getCoaches()) {
                if (coach != null) {
                    if (coach.getSeats() == null) {
                        coach.setSeats(new ArrayList<>());
                    } else {
                        for (Seat s : coach.getSeats()) {
                            if (s != null && s.getCoach() == null) {
                                s.setCoach(coach);
                            }
                        }
                    }
                }
            }
        }

        // Step 10: Save the train using TrainDao
        trainDao.saveTrain(train);
    }

}
