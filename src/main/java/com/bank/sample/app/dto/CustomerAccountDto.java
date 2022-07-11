package com.bank.sample.app.dto;

public class CustomerAccountDto {
	
	private Long customerAccountId;
	private Double balance;
	private String accountNumber;
	
	private CustomerDto customer;
	private AccountTypeDto account;
	
	
	public Long getCustomerAccountId() {
		return customerAccountId;
	}
	public void setCustomerAccountId(Long customerAccountId) {
		this.customerAccountId = customerAccountId;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		if(balance < 0) {
			return;
		}
		this.balance = balance;
	}
	public CustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	public AccountTypeDto getAccount() {
		return account;
	}
	public void setAccount(AccountTypeDto account) {
		this.account = account;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	

}
