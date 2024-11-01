package com.eventManagement.resource;

import java.io.Console;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.apache.logging.log4j.EventLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.eventManagement.dto.BookingRequestDto;
import com.eventManagement.dto.BookingResponse;
import com.eventManagement.dto.CommonApiResponse;
import com.eventManagement.entity.Booking;
import com.eventManagement.entity.Event;
import com.eventManagement.entity.Payment;
import com.eventManagement.entity.User;
import com.eventManagement.service.BookingService;
import com.eventManagement.service.EventService;
import com.eventManagement.service.PaymentService;
import com.eventManagement.service.UserService;
import com.eventManagement.service.serviceimpl.BookingServiceImpl;
import com.eventManagement.utility.Constant.BookingStatus;

@Component
public class BookingResource {

	private final Logger log = LoggerFactory.getLogger(EventResource.class);
	
	
	@Autowired
	private BookingService bookingservice;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private EventService eventservice;
	
	@Autowired
	private PaymentService paymentservice;
	
	
	BookingServiceImpl bookingserviceimpl = new BookingServiceImpl();
	
	
	
	
	public ResponseEntity<CommonApiResponse> addBooking(BookingRequestDto request) {


		CommonApiResponse response = new CommonApiResponse();

		String bookingTime = String
				.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

		if (request == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (request.getEventId() == 0 || request.getUserId() == 0 || request.getNumOfTicket() == 0
				|| request.getCvv() == null || request.getExpiryDate() == null || request.getNameOnCard() == null
				|| request.getCardNum() == null) {

			response.setResponseMessage("missing input or invalid details");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);

		}

		User customer = this.userservice.getUserById(request.getUserId());

		if (customer == null) {
			response.setResponseMessage("customer not found!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Event event = this.eventservice.getEventById(request.getEventId());

		if (event == null) {
			response.setResponseMessage("event not found!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		int eventAvailableTickets = event.getTicketAvailable();

		int noOfTicketsToBook = request.getNumOfTicket();

		if (noOfTicketsToBook > eventAvailableTickets) {
			response.setResponseMessage("Only " + eventAvailableTickets + " left for the Event!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		BigDecimal eventTicketPrice = event.getTicketPrice();

		BigDecimal totalAmountToPay = eventTicketPrice.multiply(new BigDecimal(noOfTicketsToBook));

//		String bookingId = Helper.generateEventBookingId();
//		String paymentBookingId = Helper.generateBookingPaymentId();

		Payment payment = new Payment();
		payment.setCardNumber(request.getCardNum());
		payment.setAmount(totalAmountToPay);
		payment.setUser(customer);
		payment.setCvv(request.getCvv());
		payment.setExpiryDate(request.getExpiryDate());
		payment.setNameOnCard(request.getNameOnCard());

		Payment savedPayment = this.paymentservice.addPayment(payment);

		if (savedPayment == null) {
			response.setResponseMessage("Failed to Book Event, Payment Failure!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Booking booking = new Booking();
//		booking.setId(bookingId);
		booking.setPayment(savedPayment);
		booking.setAmount(totalAmountToPay);
		booking.setUser(customer);
		booking.setEvent(event);
		booking.setNumberofTicket(noOfTicketsToBook);

		Booking savedBooking = this.bookingservice.addBooking(booking);

		if (savedBooking == null) {
			response.setResponseMessage("Failed to Book Event, Internal Error");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		event.setTicketAvailable(event.getTicketAvailable() - noOfTicketsToBook);
		this.eventservice.updateEvent(event);
		
		response.setResponseMessage("Your Booking is Confirmed, Booking ID: " );
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

	
	
	
	
	
	public ResponseEntity<BookingResponse> fetchAllBookings() {
		BookingResponse bookingresponse = new BookingResponse();
        
		List<Booking> bookings = bookingservice.getAllBooking();
        
        if(CollectionUtils.isEmpty(bookings)) {
        	bookingresponse.setResponseMessage("not found booking");
        	bookingresponse.setSuccess(false);
         
        	return new ResponseEntity<BookingResponse>(bookingresponse,HttpStatus.OK);
        }
        
        bookingresponse.setBookings(bookings);
        bookingresponse.setResponseMessage("room found successfully");
        bookingresponse.setSuccess(true);
        
        return new ResponseEntity<BookingResponse>(bookingresponse,HttpStatus.OK);
        
        
//        this method also do the same thing nothing change in the above process or in the buildingbookingresponse() method
//        return buildBookingResponse(bookings, "Bookings fetched successfully!!");
    }
	
	
	
	
	
	
	
	
	
	public ResponseEntity<BookingResponse> fetchBookingByEvent(int eventid){
		
		BookingResponse bookingrequest = new BookingResponse();
		
		Event event = eventservice.getEventById(eventid);
		
		if(event == null) {
			bookingrequest.setResponseMessage("not found");
			bookingrequest.setSuccess(false);
			
			return  new ResponseEntity<BookingResponse>(bookingrequest,HttpStatus.BAD_REQUEST);
		}
		
		List<Booking> booking = bookingservice.getBookingByEvent(event);
		
		if(booking == null) {
			bookingrequest.setResponseMessage("not fetch");
			bookingrequest.setSuccess(false);
		return new ResponseEntity<BookingResponse>(bookingrequest,HttpStatus.BAD_REQUEST);
		}
		
		bookingrequest.setBookings(booking);
		bookingrequest.setResponseMessage("ok");
		bookingrequest.setSuccess(true);
		
		return new ResponseEntity<BookingResponse>(bookingrequest,HttpStatus.OK);
		
	}
	

	
	public ResponseEntity<BookingResponse> fetchBookingByUser(int userId){
		
		BookingResponse bookingresponse = new BookingResponse();
		
		User user = userservice.getUserById(userId);
		
		if(user ==null) {
			bookingresponse.setResponseMessage("user not found");
			bookingresponse.setSuccess(false);
		}
		
		List<Booking> booking = bookingservice.getBookingByUser(user);
		
		if(booking == null) {
			bookingresponse.setResponseMessage("not found booking");
			bookingresponse.setSuccess(false);
		}
		
		bookingresponse.setBookings(booking);
		bookingresponse.setResponseMessage("found");
		bookingresponse.setSuccess(true);
		
		
		return new ResponseEntity<BookingResponse>(bookingresponse,HttpStatus.OK);
	}
	
	

	
	
	
	
	
	private boolean isvalidate(BookingRequestDto bookingrequest) {
		return bookingrequest.getCardNum() != null && bookingrequest.getCvv()!=null && bookingrequest.getEventId()!=0
				&& bookingrequest.getUserId() != 0 && bookingrequest.getNumOfTicket()!=0;
	}








	private void validateEntities(User user, Event event) throws IllegalArgumentException{
		if(user == null) throw new IllegalArgumentException("user not found");
		if(event == null) throw new IllegalArgumentException("event not found");
	}
	
	
	
	
	
	
	 private ResponseEntity<BookingResponse> buildBookingResponse(List<Booking> bookings, String successMessage) {
	        BookingResponse response = new BookingResponse();
	        response.setBookings(bookings);
	        response.setResponseMessage(CollectionUtils.isEmpty(bookings) ? "Bookings not found" : successMessage);
	        response.setSuccess(!CollectionUtils.isEmpty(bookings));
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	
}
