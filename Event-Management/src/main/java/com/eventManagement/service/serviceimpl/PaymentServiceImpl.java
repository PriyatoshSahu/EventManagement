package com.eventManagement.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.eventManagement.Repository.PaymentRepo;
import com.eventManagement.entity.Payment;
import com.eventManagement.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{

	private PaymentRepo paymentrepo;
	
	@Override
	public Payment addPayment(Payment payment) {
		return paymentrepo.save(payment);
	}
	
}
