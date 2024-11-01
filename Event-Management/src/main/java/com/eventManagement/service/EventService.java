package com.eventManagement.service;

import java.util.List;

import com.eventManagement.entity.Category;
import com.eventManagement.entity.Event;

public interface EventService {
	
	Event addEvent(Event event);
	
	Event updateEvent(Event event);
	
	List<Event> getAllEvent();
	
	Event getEventById(int id);
	
	List<Event> getEventByStatus(String status);
	
//	List<String> getAllEventCategory();
	
//	GreaterThan Keyword:
//    The GreaterThan keyword means that the method will return all Event records where the startDate is later than (or comes after) the specified startDate. In other words, it will find events that occur after the provided date.

	List<Event> findByStatusAndStartDateGreaterThan(String status, String sartDate);
	
	List<Event> findByStatusAndNameContainingIgnoreCaseAndStartDateGreaterThan(String status, String name, String startDate);
	
	List<Event> getEventByStatusAndCategoryAndStartDateGreaterThan(String status, Category category, String startDate);

	List<Event> getEventByStatusAndCategory(String status, Category category);
	
	List<Event> updateEvents(List<Event> events);

}
