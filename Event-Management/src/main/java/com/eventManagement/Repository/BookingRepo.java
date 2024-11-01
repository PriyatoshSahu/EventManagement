package com.eventManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventManagement.entity.Booking;
import com.eventManagement.entity.Event;
import com.eventManagement.entity.User;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
	List<Booking> findBookingByEvent(Event event);
	List<Booking> findBookingByUser(User user);
}
