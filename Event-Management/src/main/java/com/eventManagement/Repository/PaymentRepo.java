package com.eventManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventManagement.entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer>{

}
