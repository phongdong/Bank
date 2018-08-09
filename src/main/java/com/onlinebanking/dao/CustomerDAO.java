package com.onlinebanking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.model.Customer;
import com.onlinebanking.repository.CustomerRepository;

@Service
public class CustomerDAO 
{
	@Autowired 
	CustomerRepository customerRespository;
	
	public Customer save(Customer cust) {
		return customerRespository.save(cust);
	}
	
	public List<Customer> findAll() {
		return customerRespository.findAll();
	}
	
	public Customer findOne(Integer cust_id) {
		return customerRespository.getOne(cust_id);
	}
	
	public void delete(Customer cust) {
		customerRespository.delete(cust);
	}
}
