package com.eventManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventManagement.dto.BookingRequestDto;
import com.eventManagement.dto.BookingResponse;
import com.eventManagement.dto.CommonApiResponse;
import com.eventManagement.resource.BookingResource;

@RestController
@RequestMapping("api/booking")
public class BookingController {
	
	private final BookingResource bookingResource;

    public BookingController(BookingResource bookingResource) {
        this.bookingResource = bookingResource;	
    }
        
    
    
    
    
        @PostMapping("/add")
	public ResponseEntity<CommonApiResponse> addbooking(@RequestBody BookingRequestDto request){
		return bookingResource.addBooking(request);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<BookingResponse>fetchBooking(){
		return bookingResource.fetchAllBookings();
	}
	
	@GetMapping("/fetch/event-wise")
	public ResponseEntity<BookingResponse> fetchAllBookingByEvent(@RequestParam("id") int id){
		return bookingResource.fetchBookingByEvent(id);
	}
	
	@GetMapping("/fetch/customer-wise")
	public ResponseEntity<BookingResponse> fetchBookingByUser(@RequestParam("id") int id){
		return bookingResource.fetchBookingByUser(id);
	}
}
