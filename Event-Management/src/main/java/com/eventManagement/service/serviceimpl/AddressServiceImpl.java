package com.eventManagement.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventManagement.Repository.AddressRepo;
import com.eventManagement.entity.Address;
import com.eventManagement.service.AddressService;


@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepo addressrepo;
	
	@Override
	public Address addAddress(Address address) {
		return addressrepo.save(address);
	}

	@Override
	public Address getAddressById(int addressId) {
		return addressrepo.findById(addressId).get();
	}

	@Override
	public Address updateAddress(Address address) {
		return addressrepo.save(address);
	}

}
