package com.bank.sample.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class AccountType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountTypeId;
	@NotEmpty(message = "The above field must not be blank.")
	private String accountType;
	
	
	
	public long getAccountTypeId() {
		return accountTypeId;
	}
	public void setAccountTypeId(long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	

}
