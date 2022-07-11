package com.bank.sample.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.sample.app.dto.CustomerDto;
import com.bank.sample.app.exception.MethodArgumentNotValidException;
import com.bank.sample.app.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/customers")
	private ResponseEntity<?> createCustomer(@Valid @RequestBody(required = true) CustomerDto customerDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException();
		}
		CustomerDto savedCustomer = customerService.createCustomer(customerDto);
		return ResponseEntity.status(HttpStatus.OK).body(savedCustomer);
	}
	
	@PutMapping("/customers/{customerId}")
	private ResponseEntity<?> updateCustomer(@PathVariable long customerId,@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException();
		}
		customerDto.setCustomerId(customerId);
		CustomerDto updatedCustomer = customerService.updateCustomer(customerDto);
		return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
	}
	
	@PatchMapping("/customers/{id}")
	private ResponseEntity<?> updateCustomerPartially(@PathVariable("id") long customerId, @RequestBody Map<String, Object> fields){
		CustomerDto partiallyUpdatedCustomer = customerService.updateCustomerPartially(customerId, fields);
		return ResponseEntity.status(HttpStatus.OK).body(partiallyUpdatedCustomer);
	}
	
	@GetMapping("/customers/{id}")
	private ResponseEntity<?> getCustomer(@PathVariable("id") long customerId) {
		CustomerDto customerDetails = customerService.getCustomerDetailsById(customerId);
		return ResponseEntity.status(HttpStatus.OK).body(customerDetails);
	}
	
	@DeleteMapping("/customers/{id}")
	private ResponseEntity<?> deleteCustomer(@PathVariable("id") long customerId) {
		CustomerDto deletedCustomer = customerService.deleteCustomerById(customerId);
		return ResponseEntity.status(HttpStatus.OK).body(deletedCustomer);
	}
	
	@RequestMapping (value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
	public ResponseEntity<?> defaultPath() {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unampped Request");
	}

}
