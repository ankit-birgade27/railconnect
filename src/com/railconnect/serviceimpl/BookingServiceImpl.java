package com.railconnect.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import com.railconnect.dao.BookingDao;
import com.railconnect.model.Booking;
import com.railconnect.service.BookingService;

public class BookingServiceImpl implements BookingService{
	
    private BookingDao bookingDao;

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

	@Override
	public Booking createBooking(Booking booking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking getBookingById(String bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getAllBookings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getUserBookings(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmBooking(String bookingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelBooking(String bookingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean bookingExists(String bookingId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Booking> getBookingsByJourneyDate(LocalDate journeyDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
