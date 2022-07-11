package com.bank.sample.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.sample.app.dto.AccountTypeDto;
import com.bank.sample.app.exception.MethodArgumentNotValidException;
import com.bank.sample.app.service.AccountTypeService;

@RestController
public class AccountTypeController {
	
	@Autowired
	AccountTypeService accountTypeService;
	
	@PostMapping("/account_types")
	private ResponseEntity<?> createAccount(@Valid @RequestBody AccountTypeDto accountTypeDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException();
		}
		AccountTypeDto createdAccountType = accountTypeService.createAccount(accountTypeDto);
		return ResponseEntity.status(HttpStatus.OK).body(createdAccountType);
	}

}
