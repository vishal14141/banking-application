package com.bank.sample.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.sample.app.dto.CustomerAccountDto;
import com.bank.sample.app.service.CustomerAccountService;

@RestController
public class CustomerAccountController {
	
	@Autowired
	CustomerAccountService customerAccountService;
	
	@PostMapping("/accounts")
	private CustomerAccountDto createCustomerAccount(@RequestBody Map<String, Long> json){
		CustomerAccountDto createdCustomerAccount = customerAccountService.createCustomerAccount(json.get("customerId"), json.get("accountTypeId"));
		return createdCustomerAccount;
	}
	
	@GetMapping("/accounts/{id}/balance")
	private double getAccountBalance(@PathVariable("id") long customerAccountId) {
		double accountBalance = customerAccountService.fetchAccountBalance(customerAccountId);
		return accountBalance;
	}

}
