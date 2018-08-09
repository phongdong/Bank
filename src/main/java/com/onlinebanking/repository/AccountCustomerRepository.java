package com.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.model.AccountCustomer;

public interface AccountCustomerRepository extends JpaRepository<AccountCustomer, Integer>{

}
