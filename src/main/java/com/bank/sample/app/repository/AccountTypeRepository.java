package com.bank.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.sample.app.models.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long>{

}
