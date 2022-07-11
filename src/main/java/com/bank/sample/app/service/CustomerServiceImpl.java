package com.bank.sample.app.service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.bank.sample.app.dto.CustomerDto;
import com.bank.sample.app.exception.CustomerAlreadyExist;
import com.bank.sample.app.exception.ResourceNotFoundException;
import com.bank.sample.app.models.Customer;
import com.bank.sample.app.models.CustomerAccount;
import com.bank.sample.app.repository.CustomerAccountRepository;
import com.bank.sample.app.repository.CustomerRepository;

@Service
public class  CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerAccountRepository customerAccountRepository;
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer customerToSave = modelMapper.map(customerDto, Customer.class);
		boolean duplicateCustomer = customerRepository.existsByPhoneNumber(customerToSave.getPhoneNumber());
		if(duplicateCustomer) {
			throw new CustomerAlreadyExist();
		}
		Customer customerFromDB = customerRepository.save(customerToSave);
		CustomerDto customerToReturn = modelMapper.map(customerFromDB, CustomerDto.class);
		return customerToReturn;
	}
	
	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		Optional<Customer> customerFromDB = customerRepository.findById(customerDto.getCustomerId());
		if(customerFromDB.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		Customer customerToUpdate = customerFromDB.get();
		customerToUpdate.setAge(customerDto.getAge());
		customerToUpdate.setEmail(customerDto.getEmail());
		customerToUpdate.setPhoneNumber(customerDto.getPhoneNumber());
		
		Customer updatedCustomer = customerRepository.save(customerToUpdate);
		CustomerDto customerToReturn = modelMapper.map(updatedCustomer, CustomerDto.class);
		
		return customerToReturn;
	}
	
	@Override
	public CustomerDto updateCustomerPartially(long customerId, Map<String, Object> fieldMap) {
		Optional<Customer> optionalCustomerFromDB = customerRepository.findById(customerId);
		if(optionalCustomerFromDB.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		Customer customerFromDB = optionalCustomerFromDB.get();
		fieldMap.forEach((key, value) -> {
			Field field = ReflectionUtils.findRequiredField(Customer.class, (String)key);
			field.setAccessible(true);
			System.out.println("Reflection WOrking");
			ReflectionUtils.setField(field, customerFromDB, value);
		});
		Customer updatedCustomer = customerRepository.save(customerFromDB);
		CustomerDto customerToReturn = modelMapper.map(updatedCustomer, CustomerDto.class);
		return customerToReturn;
	}
	
	@Override
	@Transactional
	public CustomerDto deleteCustomerById(long customerId) {
		Optional<Customer> customerFromDB = customerRepository.findById(customerId);
		if(customerFromDB.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		boolean isCustomerAccountPresent = customerAccountRepository.existsByCustomer(customerFromDB.get());
		if(isCustomerAccountPresent) {
			CustomerAccount customerAccount = customerAccountRepository.findByCustomer(customerFromDB.get());
			customerAccountRepository.delete(customerAccount);
		}
		Customer customerToDelete = customerFromDB.get();
		customerRepository.delete(customerToDelete);
		
		CustomerDto deletedCustomer = modelMapper.map(customerToDelete, CustomerDto.class);
		return deletedCustomer;
	}
	
	@Override
	public CustomerDto getCustomerDetailsById(long customerId) {
		Optional<Customer> customerFromDB = customerRepository.findById(customerId);
		if(customerFromDB.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		Customer customer = customerFromDB.get();
		CustomerDto customerToReturn = modelMapper.map(customer, CustomerDto.class);
		return customerToReturn;
	}
	
	

}
