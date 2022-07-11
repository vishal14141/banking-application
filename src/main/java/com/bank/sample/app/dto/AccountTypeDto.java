package com.bank.sample.app.dto;

import javax.validation.constraints.NotEmpty;

public class AccountTypeDto {
	
	private Long accountTypeId;
	@NotEmpty(message = "The above field must not be blank.")
	private String accountType;
	
	
	
	public Long getAccountTypeId() {
		return accountTypeId;
	}
	public void setAccountTypeId(Long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	

}
