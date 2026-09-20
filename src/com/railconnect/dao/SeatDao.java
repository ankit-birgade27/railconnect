package com.railconnect.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.railconnect.enums.SeatStatus;
import com.railconnect.model.Coach;
import com.railconnect.model.Seat;
import com.railconnect.model.Train;
import com.railconnect.storage.DataStore;

public class SeatDao {

    public synchronized Seat findById(String seatId) {
        if (seatId == null) {
            return null;
        }

        // 1. Search in global seats list
        List<Seat> seats = DataStore.getSeats();
        for (Seat seat : seats) {
            if (seat != null && seatId.equalsIgnoreCase(seat.getSeatId())) {
                return seat;
            }
        }

        // 2. Search inside train coaches
        List<Train> trains = DataStore.getTrains();
        for (Train train : trains) {
            if (train != null && train.getCoaches() != null) {
                for (Coach coach : train.getCoaches()) {
                    if (coach != null && coach.getSeats() != null) {
                        for (Seat seat : coach.getSeats()) {
                            if (seat != null && seatId.equalsIgnoreCase(seat.getSeatId())) {
                                return seat;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    public synchronized void updateSeat(Seat seat) {
        if (seat == null || seat.getSeatId() == null) {
            return;
        }

        // Update in DataStore.getSeats()
        List<Seat> seats = DataStore.getSeats();
        boolean foundInGlobal = false;
        for (int i = 0; i < seats.size(); i++) {
            Seat existSeat = seats.get(i);
            if (existSeat != null && seat.getSeatId().equalsIgnoreCase(existSeat.getSeatId())) {
                seats.set(i, seat);
                foundInGlobal = true;
                break;
            }
        }
        if (!foundInGlobal) {
            seats.add(seat);
        }

        // Also update inside train coaches if present
        List<Train> trains = DataStore.getTrains();
        for (Train train : trains) {
            if (train != null && train.getCoaches() != null) {
                for (Coach coach : train.getCoaches()) {
                    if (coach != null && coach.getSeats() != null) {
                        for (int j = 0; j < coach.getSeats().size(); j++) {
                            Seat coachSeat = coach.getSeats().get(j);
                            if (coachSeat != null && seat.getSeatId().equalsIgnoreCase(coachSeat.getSeatId())) {
                                coach.getSeats().set(j, seat);
                            }
                        }
                    }
                }
            }
        }
    }

    public synchronized void saveSeat(Seat seat) {
        if (seat == null) {
            return;
        }
        updateSeat(seat);
    }

    public synchronized void saveSeats(List<Seat> seatList) {
        if (seatList == null) {
            return;
        }
        for (Seat s : seatList) {
            saveSeat(s);
        }
    }

    public synchronized Train findTrainById(int trainId) {
        List<Train> trains = DataStore.getTrains();
        for (Train train : trains) {
            if (train != null && train.getTrainId() == trainId) {
                return train;
            }
        }
        return null;
    }

    public synchronized boolean trainExists(int trainId) {
        return findTrainById(trainId) != null;
    }

    public synchronized Seat findByTrainIdAndSeatNumber(int trainId, String seatNumber) {
        Train train = findTrainById(trainId);
        if (train == null || train.getCoaches() == null || seatNumber == null) {
            return null;
        }
        String trimmedSeatNum = seatNumber.trim();
        for (Coach coach : train.getCoaches()) {
            if (coach == null || coach.getSeats() == null) {
                continue;
            }
            for (Seat seat : coach.getSeats()) {
                if (seat != null && seat.getSeatNumber() != null &&
                        seat.getSeatNumber().trim().equalsIgnoreCase(trimmedSeatNum)) {
                    return seat;
                }
            }
        }
        return null;
    }

    /**
     * Retrieves all seats belonging to the requested train.
     */
    public synchronized List<Seat> findByTrainId(int trainId) {
        List<Seat> result = new ArrayList<>();
        Train train = findTrainById(trainId);
        if (train == null) {
            return result;
        }

        Set<String> seenSeatIds = new HashSet<>();

        // 1. Gather seats from train's coaches
        if (train.getCoaches() != null) {
            for (Coach coach : train.getCoaches()) {
                if (coach != null && coach.getSeats() != null) {
                    for (Seat seat : coach.getSeats()) {
                        if (seat != null) {
                            if (seat.getCoach() == null) {
                                seat.setCoach(coach);
                            }
                            if (seat.getSeatId() != null) {
                                if (seenSeatIds.add(seat.getSeatId().trim().toUpperCase())) {
                                    result.add(seat);
                                }
                            } else {
                                result.add(seat);
                            }
                        }
                    }
                }
            }
        }

        // 2. Gather seats from DataStore.getSeats() matching the train's coaches
        List<Seat> globalSeats = DataStore.getSeats();
        if (globalSeats != null && train.getCoaches() != null) {
            for (Seat seat : globalSeats) {
                if (seat != null && seat.getCoach() != null) {
                    for (Coach coach : train.getCoaches()) {
                        if (coach != null && isCoachMatch(seat.getCoach(), coach)) {
                            if (seat.getSeatId() != null) {
                                if (seenSeatIds.add(seat.getSeatId().trim().toUpperCase())) {
                                    result.add(seat);
                                }
                            } else if (!result.contains(seat)) {
                                result.add(seat);
                            }
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * Retrieves only available seats for the requested train.
     */
    public synchronized List<Seat> findAvailableSeatsByTrainId(int trainId) {
        List<Seat> allSeats = findByTrainId(trainId);
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : allSeats) {
            if (seat != null && isSeatAvailableStatus(seat.getStatus())) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    private boolean isCoachMatch(Coach c1, Coach c2) {
        if (c1 == c2) {
            return true;
        }
        if (c1.getCoachId() > 0 && c2.getCoachId() > 0 && c1.getCoachId() == c2.getCoachId()) {
            return true;
        }
        if (c1.getCoachNumber() != null && c2.getCoachNumber() != null &&
                c1.getCoachNumber().trim().equalsIgnoreCase(c2.getCoachNumber().trim())) {
            return true;
        }
        return false;
    }

    private boolean isSeatAvailableStatus(String status) {
        if (status == null) {
            return false;
        }
        return SeatStatus.AVAILABLE.name().equalsIgnoreCase(status.trim());
    }

    public synchronized void clear() {
        DataStore.getSeats().clear();
    }
}

