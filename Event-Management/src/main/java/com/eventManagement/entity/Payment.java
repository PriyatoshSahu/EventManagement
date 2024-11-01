package com.eventManagement.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
public class Payment {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nameOnCard;
	private String cvv;
	private String cardNumber;
	private String expiryDate;
	private BigDecimal amount;
	
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User user;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Payment(int id, String nameOnCard, String cvv, String cardNumber, String expiryDate, BigDecimal amount, User user) {
		super();
		this.id = id;
		this.nameOnCard = nameOnCard;
		this.cvv = cvv;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.amount = amount;
		this.user = user;
	}


	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
