package com.eventManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventManagement.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

}