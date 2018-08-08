package com.onlinebanking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.model.Bank;
import com.onlinebanking.repository.BankRepository;

@Service
public class BankDAO 
{
	@Autowired
	BankRepository bankRepository;
	
	public Bank save(Bank bank) {
		return bankRepository.save(bank);
	}
	
	public List<Bank> findAll() {
		return bankRepository.findAll();
	}
	
	public Bank findOne(Integer bankId) {
		return bankRepository.getOne(bankId);
	}
	
	public void delete(Bank bank) {
		bankRepository.delete(bank);
	}
}
