package com.eventManagement.entity;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String eventName;
	private String description;
	private String venueName;
	private String venueType;
	private String location;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;
	
	private BigDecimal ticketPrice;
	private int totalTicket;	
	private int ticketAvailable;
	private String image;
	private String status;
	
	private String startDate; // event start date in current millis time

	private String endDate; // event end date in current millis time

	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	public String getVenueType() {
		return venueType;
	}
	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public int getTotalTicket() {
		return totalTicket;
	}
	public void setTotalTicket(int totalTicket) {
		this.totalTicket = totalTicket;
	}
	public int getTicketAvailable() {
		return ticketAvailable;
	}
	public void setTicketAvailable(int ticketAvailable) {
		this.ticketAvailable = ticketAvailable;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	public Event(int id, String eventName, String description, String venueName, String venueType, String location,
			Category category, BigDecimal ticketPrice, int totalTicket, int ticketAvailable, String image, String status,
			String startDate , String endDate) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.description = description;
		this.venueName = venueName;
		this.venueType = venueType;
		this.location = location;
		this.category = category;
		this.ticketPrice = ticketPrice;
		this.totalTicket = totalTicket;
		this.ticketAvailable = ticketAvailable;
		this.image = image;
		this.status = status;
		this.startDate=startDate;
		this.endDate=endDate;
	}
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
}
