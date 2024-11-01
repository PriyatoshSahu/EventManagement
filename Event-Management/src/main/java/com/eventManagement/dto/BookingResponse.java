package com.eventManagement.dto;

import java.util.ArrayList;
import java.util.List;

import com.eventManagement.entity.Booking;

public class BookingResponse extends CommonApiResponse{
	public BookingResponse(String responseMessage, boolean isSuccess) {
		super(responseMessage, isSuccess);
		// TODO Auto-generated constructor stub
	}

	private List<Booking> bookings = new ArrayList();

	
	
	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public BookingResponse(String responseMessage, boolean isSuccess, List<Booking> bookings) {
		super(responseMessage, isSuccess);
		this.bookings = bookings;
	}

	public BookingResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
