package com.eventManagement.dto;

import java.util.ArrayList;
import java.util.List;

import com.eventManagement.entity.Event;

public class EventResponseDto extends CommonApiResponse {
	private List<Event> events = new ArrayList<Event>();

	
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public EventResponseDto(List<Event> events) {
		super();
		this.events = events;
	}

	public EventResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
