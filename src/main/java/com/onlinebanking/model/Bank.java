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
@Table(name="bank")
@EntityListeners(AuditingEntityListener.class)

public class Bank 
{
	@Id
	@Column(name="bank_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	int bankId;
	
	@Column(name="bank_name")
	String bankName;

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
