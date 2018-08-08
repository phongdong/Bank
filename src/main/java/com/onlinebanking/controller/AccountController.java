package com.onlinebanking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.dao.AccountDAO;
import com.onlinebanking.model.Account;

@RestController
@RequestMapping("/bank")
public class AccountController 
{
	@Autowired
	AccountDAO accountDAO;
	
	@PostMapping("/accounts")
	public Account createAccount(@Valid @RequestBody Account acct) {
		return accountDAO.save(acct);
	}
	
	@GetMapping("/accounts")
	public List<Account> getAllAccount() {
		return accountDAO.findAll();
	}
	
	@GetMapping("/accounts/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable(value="id") Integer acctId) {
		Account account = accountDAO.findOne(acctId);
		if (account == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(account);
	}
	
	@PutMapping(value="/accounts/{id}", produces="application/json")
	public ResponseEntity<Account> updateAccount(@PathVariable(value="id") Integer acctId, 
			@Valid @RequestBody Account newAccount) {
		Account account = accountDAO.findOne(acctId);
		if (account == null) { 
			return ResponseEntity.notFound().build();
		}
		
		account.setAmount(newAccount.getAmount());
		accountDAO.save(account);
		return ResponseEntity.ok().body(account);
	}
	
	@PutMapping(value="/accounts/{id}")
	
	@DeleteMapping(value="/accounts/{id}")
	public ResponseEntity<Account> deleteAccount(@PathVariable(value="id") Integer acctId) {
		Account account = accountDAO.findOne(acctId);
		if (account == null) {
			return ResponseEntity.notFound().build();
		}
		accountDAO.delete(account);
		return ResponseEntity.ok().build();
	}
	
	
}
