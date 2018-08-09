package com.onlinebanking.controller;

import java.awt.datatransfer.Transferable;
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
import com.onlinebanking.dao.BankDAO;
import com.onlinebanking.model.Account;
import com.onlinebanking.model.Bank;

@RestController
@RequestMapping("/bank/{bankId}")
public class AccountController 
{
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	BankDAO bankDAO;
	
	@PostMapping("/accounts")
	public ResponseEntity<Account> createAccount(@PathVariable(value="bankId") Integer bankId, @Valid @RequestBody Account acct) {
		Bank bank = bankDAO.findOne(bankId);
		if (bank == null) {
			return ResponseEntity.notFound().build();
		}
		((Account) acct).setBank(bank);
		Account account = accountDAO.save(acct);
		return ResponseEntity.ok().body(account);
	}
	
	@GetMapping("/accounts")
	public List<Account> getAllAccount() {
		return accountDAO.findAll();
	}
	
	@GetMapping("/accounts/{acctId}")
	public ResponseEntity<Account> getAccountById(@PathVariable(value="acctId") Integer acctId) {
		Account account = accountDAO.findOne(acctId);
		if (account == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(account);
	}
	
	@PutMapping(value="/accounts/{acctId}", produces="application/json")
	public ResponseEntity<Account> updateAccount(@PathVariable(value="acctId") Integer acctId, 
			@Valid @RequestBody Account newAccount) {
		Account account = accountDAO.findOne(acctId);
		if (account == null) { 
			return ResponseEntity.notFound().build();
		}
		
		if (account.getAmount() + newAccount.getAmount() < 0) {
			return ResponseEntity.badRequest().build();
		}
		
		account.setAmount(account.getAmount() + newAccount.getAmount());
		accountDAO.save(account);
		return ResponseEntity.ok().body(account);
	}
	
	@PutMapping(value="/accounts/{fromAcctId}/{toAcctId}/{amount}", produces="application/json")
	public ResponseEntity<Account> transfer(@PathVariable(value="fromAcctId") Integer fromAcctId,
			@PathVariable(value="toAcctId") Integer toAcctId, @PathVariable(value="amount") Integer amountTransfer) {
		Account fromAcct = accountDAO.findOne(fromAcctId);
		if (fromAcct == null) {
			return ResponseEntity.notFound().build();
		}
		Account toAccount = accountDAO.findOne(toAcctId);
		if (toAccount == null) {
			return ResponseEntity.notFound().build();
		}
		
		if (fromAcct.getAmount() < amountTransfer) {
			return ResponseEntity.notFound().build();
		}
		
		fromAcct.setAmount(fromAcct.getAmount() - amountTransfer);
		toAccount.setAmount(toAccount.getAmount() + amountTransfer);
		accountDAO.save(fromAcct);
		accountDAO.save(toAccount);
		return ResponseEntity.ok().body(toAccount);
	}
	
	
	@DeleteMapping(value="/accounts/{acctId}")
	public ResponseEntity<Account> deleteAccount(@PathVariable(value="acctId") Integer acctId) {
		Account account = accountDAO.findOne(acctId);
		if (account == null) {
			return ResponseEntity.notFound().build();
		}
		accountDAO.delete(account);
		return ResponseEntity.ok().build();
	}
	
	
}
