package com.onlinebanking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Bank")
@EntityListeners(AuditingEntityListener.class)

public class Bank {
	@Id
	@Column(name="bankId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	int bankId;
	
	@Column(name="name")
	String name;
	
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public int getBankId() {
		return bankId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
