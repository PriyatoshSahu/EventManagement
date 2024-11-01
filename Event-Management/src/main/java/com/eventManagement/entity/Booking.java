package com.eventManagement.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private BigDecimal amount;
	
	private int numberofTicket;
	
	
	@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;


	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public int getNumberofTicket() {
		return numberofTicket;
	}


	public void setNumberofTicket(int numberofTicket) {
		this.numberofTicket = numberofTicket;
	}


	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	public Booking(int id, Event event, User user, BigDecimal amount, int numberofTicket, Payment payment) {
		super();
		this.id = id;
		this.event = event;
		this.user = user;
		this.amount = amount;
		this.numberofTicket = numberofTicket;
		this.payment = payment;
	}


	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
