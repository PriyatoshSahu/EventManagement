package com.eventManagement.dto;

public class BookingRequestDto {
	
	private int eventId;
	
	private int userId;
	
	private int numOfTicket;	
	
	private String cardNum;
	
	private String nameOnCard;
	
	private String cvv;
	
	private String expiryDate;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getNumOfTicket() {
		return numOfTicket;
	}

	public void setNumOfTicket(int numOfTicket) {
		this.numOfTicket = numOfTicket;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BookingRequestDto(int eventId, int userId, int numOfTicket, String cardNum, String nameOnCard, String cvv,
			String expiryDate) {
		super();
		this.eventId = eventId;
		this.userId = userId;
		this.numOfTicket = numOfTicket;
		this.cardNum = cardNum;
		this.nameOnCard = nameOnCard;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
	}
	
	
}
