package com.eventManagement.dto;


import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.eventManagement.entity.Event;

import lombok.Data;


public class AddEventRequestDto {

	private int id;

	private String name;

	private String description;

	private String venueName;

	private String venueType;

	private String location;

	private int noOfTickets;
	
	private int availableTickets;

	private String startDate;

	private String endDate;

	private BigDecimal ticketPrice;

	private MultipartFile image;

	private int categoryId;


	
	
	
	
	
	public AddEventRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}





	public AddEventRequestDto(int id, String name, String description, String venueName, String venueType,
			String location, int noOfTickets, int availableTickets, String startDate, String endDate,
			BigDecimal ticketPrice, MultipartFile image, int categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.venueName = venueName;
		this.venueType = venueType;
		this.location = location;
		this.noOfTickets = noOfTickets;
		this.availableTickets = availableTickets;
		this.startDate = startDate;
		this.endDate = endDate;
		this.ticketPrice = ticketPrice;
		this.image = image;
		this.categoryId = categoryId;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
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





	public int getNoOfTickets() {
		return noOfTickets;
	}





	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}





	public int getAvailableTickets() {
		return availableTickets;
	}





	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}





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





	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}





	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}





	public MultipartFile getImage() {
		return image;
	}





	public void setImage(MultipartFile image) {
		this.image = image;
	}





	public int getCategoryId() {
		return categoryId;
	}





	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}





	

	
	

	public static Event toEntity(AddEventRequestDto dto) {
		Event entity = new Event();
		BeanUtils.copyProperties(dto, entity, "image", "categoryId");
		return entity;
	}

}
