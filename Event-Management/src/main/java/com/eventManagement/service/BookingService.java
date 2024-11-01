package com.eventManagement.service;

import java.util.List;

import com.eventManagement.dto.BookingRequestDto;
import com.eventManagement.entity.Booking;
import com.eventManagement.entity.Event;
import com.eventManagement.entity.User;

public interface BookingService {
	
	Booking addBooking(Booking booking);
	
	Booking UpdateBookin(Booking booking);
	
	List<Booking> getAllBooking();
	
	Booking getBookingById(int bookingId);
	
	void deleteBookingById(int bookingid);
	
	List<Booking> getBookingByEvent(Event event);
	
	List<Booking> getBookingByUser(User user);

//	Booking createBooking(BookingRequestDto bookingrequest, User user, Event event);
	
//	Booking getBookingByBookingId(int bookingId);
}
