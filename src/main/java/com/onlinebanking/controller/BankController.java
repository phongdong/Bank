package com.onlinebanking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.dao.BankDAO;
import com.onlinebanking.model.Bank;

@RestController
@RequestMapping("/bank")
public class BankController 
{
	@Autowired
	BankDAO bankDAO;
	
	@PostMapping("")
	public Bank createBank(@Valid @RequestBody Bank bank) {
		return bankDAO.save(bank);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Bank> updateBank(@PathVariable(value="id") Integer bankId, 
			@Valid @RequestBody Bank newBank) {
		Bank bank = bankDAO.findOne(bankId);
		if (bank == null) {
			return ResponseEntity.notFound().build();
		}
		
		bank.setName(newBank.getName());
		bankDAO.save(bank);
		return ResponseEntity.ok().body(bank);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Bank> deleteBank(@PathVariable(value="id") Integer bankId) {
		Bank bank = bankDAO.findOne(bankId);
		if (bank == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
		
	}
}
