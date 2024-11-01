package com.eventManagement.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventManagement.Repository.EventRepo;
import com.eventManagement.entity.Category;
import com.eventManagement.entity.Event;
import com.eventManagement.service.EventService;

@Service
public class EventServiceImpl implements EventService{

	@Autowired
	private EventRepo eventrepo;
	
	@Override
	public Event addEvent(Event event) {
		return eventrepo.save(event);
	}

	@Override
	public Event updateEvent(Event event) {
		return eventrepo.save(event);
	}

	@Override
	public List<Event> getAllEvent() {
		return eventrepo.findAll();
	}

	@Override
	public Event getEventById(int id) {
		return eventrepo.findById(id).get();
	}

	@Override
	public List<Event> getEventByStatus(String status) {
		return eventrepo.findByStatusOrderByIdDesc(status);
	}

//	@Override
//	public List<String> getAllEventCategory() {
//		return eventrepo.findDistinctCategory();
//	}

	@Override
	public List<Event> findByStatusAndStartDateGreaterThan(String status, String sartDate) {
		return eventrepo.findByStatusAndStartDateGreaterThan(status, sartDate);
	}

	@Override
	public List<Event> findByStatusAndNameContainingIgnoreCaseAndStartDateGreaterThan(String status, String name,
			String startDate) {
		return eventrepo.findByStatusAndEventNameContainingIgnoreCaseAndStartDateGreaterThan(status, name, startDate);
	}

	@Override
	public List<Event> getEventByStatusAndCategoryAndStartDateGreaterThan(String status, Category category,
			String startDate) {
		return this.eventrepo.findByStatusAndCategoryAndStartDateGreaterThan(status, category, startDate);

	}

	@Override
	public List<Event> getEventByStatusAndCategory(String status, Category category) {
		// TODO Auto-generated method stub
		return this.eventrepo.findByStatusAndCategory(status, category);
	}

	@Override
	public List<Event> updateEvents(List<Event> events) {
		// TODO Auto-generated method stub
		return this.eventrepo.saveAll(events);
	}

	
}
