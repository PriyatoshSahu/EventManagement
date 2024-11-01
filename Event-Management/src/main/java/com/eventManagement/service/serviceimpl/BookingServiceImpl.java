package com.eventManagement.service.serviceimpl;

import java.math.BigDecimal;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventManagement.Repository.BookingRepo;
import com.eventManagement.dto.BookingRequestDto;
import com.eventManagement.entity.Booking;
import com.eventManagement.entity.Event;
import com.eventManagement.entity.Payment;
import com.eventManagement.entity.User;
import com.eventManagement.service.BookingService;
import com.eventManagement.service.EventService;
import com.eventManagement.service.PaymentService;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepo bookingrepo;
	
	
	@Override
	public Booking addBooking(Booking booking) {
		return bookingrepo.save(booking);
	}

	@Override
	public Booking UpdateBookin(Booking booking) {
		return bookingrepo.save(booking);
	}

	@Override
	public List<Booking> getAllBooking() {
		return bookingrepo.findAll();
	}

	@Override
	public Booking getBookingById(int bookingId) {
		return bookingrepo.findById(bookingId).get();
	}

	@Override
	public void deleteBookingById(int bookingid) {
		bookingrepo.deleteById(bookingid);
	}

	@Override
	public List<Booking> getBookingByEvent(Event event) {
		return bookingrepo.findBookingByEvent(event);
	}

	@Override
	public List<Booking> getBookingByUser(User user) {
		return bookingrepo.findBookingByUser(user);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	public Booking createBooking(BookingRequestDto bookingrequest, User user, Event event) {
//		
//		int ticketAvailable = event.getTicketAvailable();
//		int ticketToBook  = bookingrequest.getNumOfTicket();
//		
//		if(ticketToBook > ticketAvailable) {
//			throw new IllegalArgumentException("only"+ ticketAvailable + "available");
//		}
//		
//		BigDecimal eventTicketPrice = event.getTicketPrice();
//		BigDecimal totalAmountToPay = eventTicketPrice.multiply(new BigDecimal(ticketToBook));
//		
//		
//		Payment payment = new Payment();
//		payment.setAmount(totalAmountToPay);
//		payment.setCardNumber(bookingrequest.getCardNum());
//		payment.setCvv(bookingrequest.getCvv());
//		payment.setExpiryDate(bookingrequest.getExpiryDate());
//		payment.setNameOnCard(bookingrequest.getNameOnCard());
//		payment.setUser(user);
//		
//		Payment savepayment = paymentService.addPayment(payment);
//		
//		if(savepayment==null) {
//			throw new RuntimeException("not done");
//		}
//		
//		
//		
//		Booking booking = new Booking();
//		booking.setAmount(totalAmountToPay);
//		booking.setEvent(event);
//		booking.setNumberofTicket(ticketToBook);
//		booking.setPayment(savepayment);
//		booking.setUser(user);
//		
//		Booking savebooking = bookingservice.addBooking(booking);
//		
//		if(savebooking==null) {
//			throw new RuntimeException("booking not done");
//		}
//		
//		event.setTicketAvailable(ticketAvailable - ticketToBook);
//		eventservice.updateEvent(event);
//		
//		return savebooking;
//		
//		
//		
//		
//		
//	}

}
