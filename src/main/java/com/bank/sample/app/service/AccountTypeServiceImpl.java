package com.bank.sample.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.sample.app.dto.AccountTypeDto;
import com.bank.sample.app.models.AccountType;
import com.bank.sample.app.repository.AccountTypeRepository;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {
	
	@Autowired
	AccountTypeRepository accountTypeRepository;
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public AccountTypeDto createAccount(AccountTypeDto accountDto) {
		AccountType accountToCreate = modelMapper.map(accountDto, AccountType.class);
		AccountType savedAccount = accountTypeRepository.save(accountToCreate);
		AccountTypeDto accountToReturn = modelMapper.map(savedAccount, AccountTypeDto.class);
		return accountToReturn;
	}

}
