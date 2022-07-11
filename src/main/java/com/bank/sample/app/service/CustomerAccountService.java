package com.bank.sample.app.service;

import com.bank.sample.app.dto.CustomerAccountDto;

public interface CustomerAccountService {
	
	public CustomerAccountDto createCustomerAccount(long customerId, long accountTypeId);
	public double fetchAccountBalance(long accountId);
}
