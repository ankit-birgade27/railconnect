package com.railconnect.service;

import java.time.LocalDate;
import java.util.List;

import com.railconnect.model.Booking;

public interface BookingService {

    Booking createBooking(Booking booking);

    Booking getBookingById(String bookingId);

    List<Booking> getAllBookings();

    List<Booking> getUserBookings(int userId);

    void confirmBooking(String bookingId);

    void cancelBooking(String bookingId);

    boolean bookingExists(String bookingId);

    List<Booking> getBookingsByJourneyDate(LocalDate journeyDate);
}