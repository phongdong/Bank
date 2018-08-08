package com.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

}
