package com.bank.sample.app.service;

import java.util.Map;

import com.bank.sample.app.dto.CustomerDto;

public interface CustomerService {
	
	public CustomerDto createCustomer(CustomerDto customerDto);
	public CustomerDto updateCustomer(CustomerDto customerDto);
	public CustomerDto updateCustomerPartially(long customerId, Map<String, Object> fieldMap);
	public CustomerDto deleteCustomerById(long customerId);
	public CustomerDto getCustomerDetailsById(long customerId);
	
	

}
