package com.onlinebanking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.dao.AccountCustomerDAO;
import com.onlinebanking.dao.AccountDAO;
import com.onlinebanking.dao.CustomerDAO;
import com.onlinebanking.model.Account;
import com.onlinebanking.model.AccountCustomer;
import com.onlinebanking.model.Customer;

@RestController
@RequestMapping("/bank/1")
public class AccountCustomerController 
{
	@Autowired
	AccountCustomerDAO accountCustomerDAO;
	
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired 
	CustomerDAO customerDAO;
	
	@PostMapping("/account_customer")
	public ResponseEntity<AccountCustomer> createAccountCustomer(@Valid @RequestBody AccountCustomer acct_cust) {
		Integer acctId = acct_cust.getAccount().getAcctId();
		Account account = accountDAO.findOne(acctId);
		
		if (account == null) {
			return ResponseEntity.notFound().build();
		}
		
		Integer custId = acct_cust.getCustomer().getCustId();
		Customer customer = customerDAO.findOne(custId);
		
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		
		AccountCustomer accountCustomer = accountCustomerDAO.save(acct_cust);
		
		return ResponseEntity.ok().body(accountCustomer);
		
	}

}
