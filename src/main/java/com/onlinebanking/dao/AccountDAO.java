package com.onlinebanking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.model.Account;
import com.onlinebanking.repository.AccountRepository;

@Service
public class AccountDAO 
{
	@Autowired 
	AccountRepository accountRepository;
	
	public Account save(Account acct) {
		return accountRepository.save(acct);
	}
	
	public List<Account> findAll() {
		return accountRepository.findAll();
	}
	
	public Account findOne(Integer acctId) {
		return accountRepository.getOne(acctId);
	}
	
	public void delete(Account acct) {
		accountRepository.delete(acct);
	}

}
