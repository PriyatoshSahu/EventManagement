package com.eventManagement.service;

import com.eventManagement.entity.Address;

public interface AddressService {
	Address addAddress(Address address);
	
	Address getAddressById(int adressId);
	
	Address updateAddress(Address address);
	
	
}
