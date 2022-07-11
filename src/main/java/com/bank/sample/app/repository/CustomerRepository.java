package com.bank.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.sample.app.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	boolean existsByPhoneNumber(String phoneNumber);

}
