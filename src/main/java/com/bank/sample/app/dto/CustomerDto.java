package com.bank.sample.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

public class CustomerDto {
	
	private Long customerId;
	@NonNull
	private Integer age;
	@NotEmpty(message = "The above field must not be blank.")
	private String name;
	@NotEmpty(message = "The above field must not be blank.")
	@Email
	private String email;
	@NotEmpty(message = "The above field must not be blank.")
	private String phoneNumber;
	
	private AccountTypeDto accountType;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public AccountTypeDto getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountTypeDto accountType) {
		this.accountType = accountType;
	}
	
	

}
