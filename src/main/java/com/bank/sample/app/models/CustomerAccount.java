package com.bank.sample.app.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CustomerAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerAccountId;
	private double balance;
	private String accountNumber;
	
	@OneToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
	private Customer customer;
	@OneToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
	private AccountType account;
	
	public long getCustomerAccountId() {
		return customerAccountId;
	}
	public void setCustomerAccountId(long customerAccountId) {
		this.customerAccountId = customerAccountId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public AccountType getAccount() {
		return account;
	}
	public void setAccount(AccountType account) {
		this.account = account;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	
	

}
