package com.railconnect.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.dao.SeatDao;
import com.railconnect.dao.TrainDao;
import com.railconnect.enums.SeatStatus;
import com.railconnect.exception.*;
import com.railconnect.model.Seat;
import com.railconnect.model.Train;
import com.railconnect.service.SeatService;

public class SeatServiceImpl implements SeatService {

    private SeatDao seatDao;
    private TrainDao trainDao;

    public SeatServiceImpl() {
        this.seatDao = new SeatDao();
        this.trainDao = new TrainDao();
    }

    public SeatServiceImpl(SeatDao seatDao) {
        this.seatDao = seatDao;
        this.trainDao = new TrainDao();
    }

    public SeatServiceImpl(SeatDao seatDao, TrainDao trainDao) {
        this.seatDao = seatDao;
        this.trainDao = trainDao;
    }

    /**
     * Group 1 Assigned Method: Retrieves all seats currently available for a particular train.
     *
     * Required Processing:
     * 1. Validate the train ID.
     * 2. Check whether the train exists.
     * 3. Retrieve the seats associated with the train.
     * 4. Check the status of each seat.
     * 5. Select only seats whose status indicates that they are available.
     * 6. Return the list of available seats.
     */
    @Override
    public List<Seat> getAvailableSeats(int trainId) {
        // Step 1: Validate the train ID
        if (trainId <= 0) {
            throw new InvalidTrainIdException("Invalid train ID: " + trainId + ". Train ID must be a positive integer.");
        }

        // Step 2: Check whether the train exists
        boolean exists = (trainDao != null && trainDao.findById(trainId) != null) ||
                         (seatDao != null && seatDao.trainExists(trainId));
        if (!exists) {
            throw new TrainNotFoundException("Train not found with ID: " + trainId);
        }

        // Step 3: Retrieve the seats associated with the train
        List<Seat> seats = seatDao.findByTrainId(trainId);

        // Step 4 & 5: Check the status of each seat and select only seats with status AVAILABLE
        List<Seat> availableSeats = new ArrayList<>();
        if (seats != null) {
            for (Seat seat : seats) {
                if (seat != null && seat.getStatus() != null &&
                        SeatStatus.AVAILABLE.name().equalsIgnoreCase(seat.getStatus().trim())) {
                    availableSeats.add(seat);
                }
            }
        }

        // Step 6: Return the list of available seats
        return availableSeats;
    }

    @Override
    public Seat getSeatById(String seatId) {
        if (seatId == null || seatId.trim().isEmpty()) {
            throw new InvalidSeatIdException("Seat ID cannot be null or blank.");
        }
        Seat seat = seatDao.findById(seatId);
        if (seat == null) {
            throw new SeatNotFoundException("Seat not found with ID: " + seatId);
        }
        return seat;
    }

    @Override
    public Seat getSeatByNumber(int trainId, String seatNumber) {
        // 1. Validate Train ID
        if (trainId <= 0) {
            throw new InvalidTrianIdException("Train ID must be positive");
        }

        // 2. Validate Seat Number
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            throw new InvalidSeatNumberException("Seat number cannot be empty");
        }

        // 3. Check whether train exists
        Train train = (trainDao != null) ? trainDao.findById(trainId) : new TrainDao().findById(trainId);
        if (train == null) {
            throw new TrainNotFoundException("Train not found with ID: " + trainId);
        }

        // 4. Search seat
        Seat seat = seatDao.findByTrainIdAndSeatNumber(trainId, seatNumber.trim());

        // 5. Check whether seat exists
        if (seat == null) {
            throw new SeatNotFoundException("Seat not found with number: " + seatNumber);
        }

        // 6. Return seat
        return seat;
    }

    @Override
    public boolean isSeatAvailable(int trainId, String seatNumber)
            throws InvalidSeatNumberException, SeatNotFoundException,
            InvalidTrianIdException, TrainNotFoundException {

        if (trainId <= 0) {
            throw new InvalidTrianIdException("Train ID must be positive");
        }

        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            throw new InvalidSeatNumberException("Seat number cannot be empty");
        }

        boolean exists = (trainDao != null && trainDao.findById(trainId) != null) ||
                         (seatDao != null && seatDao.trainExists(trainId));
        if (!exists) {
            throw new TrainNotFoundException("Train not found with this ID: " + trainId);
        }

        Seat seat = seatDao.findByTrainIdAndSeatNumber(trainId, seatNumber);
        if (seat == null) {
            throw new SeatNotFoundException("Seat not found");
        }

        return "AVAILABLE".equalsIgnoreCase(seat.getStatus());
    }

    @Override
    public void reserveSeat(String seatId) {
        if (seatId == null || seatId.trim().isEmpty()) {
            throw new InvalidSeatIdException("Invalid Seat ID");
        }
        Seat seat = seatDao.findById(seatId);
        if (seat == null) {
            throw new SeatNotFoundException("Seat not found");
        }
        seat.setStatus(SeatStatus.RESERVED.name());
        seatDao.updateSeat(seat);
    }

    @Override
    public void releaseSeat(String seatId) {
        if (seatId == null || seatId.isBlank()) {
            throw new InvalidSeatIdException("Invalid Seat ID");
        }

        Seat seat = seatDao.findById(seatId);
        if (seat == null) {
            throw new SeatNotFoundException("Seat not found");
        }

        if (SeatStatus.AVAILABLE.name().equals(seat.getStatus())) {
            throw new SeatAlreadyAvailableException("Seat is already Available.");
        }

        seat.setStatus(SeatStatus.AVAILABLE.name());
        seatDao.updateSeat(seat);
    }
}
