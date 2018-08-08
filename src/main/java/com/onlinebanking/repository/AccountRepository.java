package com.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
