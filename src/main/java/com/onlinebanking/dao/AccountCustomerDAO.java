package com.onlinebanking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.model.AccountCustomer;
import com.onlinebanking.repository.AccountCustomerRepository;

@Service
public class AccountCustomerDAO 
{
	@Autowired
	AccountCustomerRepository accountCustomerRepository;
	
	public AccountCustomer save(AccountCustomer acct_cust) {
		return accountCustomerRepository.save(acct_cust);
	}
	
	public List<AccountCustomer> findAll() {
		return accountCustomerRepository.findAll();
	}
	
	public AccountCustomer findOne(Integer acct_cust_id) {
		return accountCustomerRepository.getOne(acct_cust_id);
	}
	
	public void delete(AccountCustomer acct_cust) {
		accountCustomerRepository.delete(acct_cust);
	}
}
