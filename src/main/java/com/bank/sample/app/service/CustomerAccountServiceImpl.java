package com.bank.sample.app.service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.sample.app.dto.CustomerAccountDto;
import com.bank.sample.app.exception.BadRequestException;
import com.bank.sample.app.exception.ResourceNotFoundException;
import com.bank.sample.app.models.AccountType;
import com.bank.sample.app.models.Customer;
import com.bank.sample.app.models.CustomerAccount;
import com.bank.sample.app.repository.AccountTypeRepository;
import com.bank.sample.app.repository.CustomerAccountRepository;
import com.bank.sample.app.repository.CustomerRepository;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {
	
	@Autowired
	CustomerAccountRepository customerAccountRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AccountTypeRepository accountRepository;
	@Autowired
	ModelMapper modelMapper;

	
	
	@Override
	@Transactional
	public CustomerAccountDto createCustomerAccount(long customerId, long accountTypeId) {
		Optional<Customer> customerFromDB = customerRepository.findById(customerId);
		Optional<AccountType> accountFromDB = accountRepository.findById(accountTypeId);
		if(customerFromDB.isEmpty() || accountFromDB.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		Customer customerToUpdate = customerFromDB.get();
		customerToUpdate.setAccountType(accountFromDB.get());
		Customer updatedCustomer = customerRepository.save(customerToUpdate);
		
		CustomerAccount accountToCreate = new CustomerAccount();
		accountToCreate.setAccount(accountFromDB.get());
		accountToCreate.setCustomer(updatedCustomer);
		accountToCreate.setBalance(0);
		accountToCreate.setAccountNumber(generateAccountNumber(10));
		// Also implement condition to check whether this account number is unique or not. 
		CustomerAccount createdAccount = customerAccountRepository.save(accountToCreate);
		CustomerAccountDto accountToReturn = modelMapper.map(createdAccount, CustomerAccountDto.class);
		return accountToReturn;
	}

	@Override
	public double fetchAccountBalance(long accountId){
		Optional<CustomerAccount> customerAccount = customerAccountRepository.findById(accountId);
		if(customerAccount.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		if(customerAccount.get().getAccount().getAccountType().contentEquals("LOAN")) {
			throw new BadRequestException();
		}
		double accountBalance = customerAccount.get().getBalance();
		return accountBalance;
	}
	
	private String generateAccountNumber(int length) {

		final String KEY_GENERATION = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		
		// each iteration of the loop randomly chooses a character from the given
		// ASCII range and appends it to the `StringBuilder` instance
		return IntStream.range(0, length).map(i -> random.nextInt(KEY_GENERATION.length()))
				.mapToObj(randomIndex -> String.valueOf(KEY_GENERATION.charAt(randomIndex))).collect(Collectors.joining());

	}
	
}
