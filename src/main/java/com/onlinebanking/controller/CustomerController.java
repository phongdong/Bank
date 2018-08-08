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

import com.onlinebanking.dao.CustomerDAO;
import com.onlinebanking.model.Customer;

@RestController
@RequestMapping("/bank")
public class CustomerController 
{
	@Autowired
	CustomerDAO customerDAO;
	
	@PostMapping("/customers")
	public Customer createCustomer(@Valid @RequestBody Customer cust) {
		return customerDAO.save(cust);
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return customerDAO.findAll();
	}
	
	@GetMapping(value="/customers/{id}", produces = "application/json")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value="id") Integer custId) {
		Customer customer = customerDAO.findOne(custId);
		
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(customer);
	}
	
	@PutMapping(value="/customers/{id}", produces = "application/json")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value="id") Integer custId,
			@Valid @RequestBody Customer newCustomer) {
		Customer customer = customerDAO.findOne(custId);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		
		customer.setFirstName(newCustomer.getFirstName());
		customer.setLastName(newCustomer.getLastName());
		
		Customer updateCustomer = customerDAO.save(customer);
		return ResponseEntity.ok().body(updateCustomer);
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable(value="id") Integer custId) {
		Customer customer = customerDAO.findOne(custId);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		
		customerDAO.delete(customer);
		return ResponseEntity.ok().build();
	}
}
