package com.bank.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.sample.app.models.Customer;
import com.bank.sample.app.models.CustomerAccount;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long>{
	
	public CustomerAccount findByCustomer(Customer customer);
	public boolean existsByCustomer(Customer customer);

}
