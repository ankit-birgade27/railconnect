package com.railconnect.service;

import java.util.List;

import com.railconnect.model.Seat;

public interface SeatService {

    List<Seat> getAvailableSeats(int trainId);

    Seat getSeatById(String seatId);

    Seat getSeatByNumber(int trainId, String seatNumber);

    boolean isSeatAvailable(int trainId, String seatNumber);

    void reserveSeat(String seatId);

    void releaseSeat(String seatId);
}