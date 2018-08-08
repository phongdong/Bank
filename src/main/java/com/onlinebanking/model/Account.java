package com.onlinebanking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Account")
@EntityListeners(AuditingEntityListener.class)

public class Account
{
	@Id
	@Column(name="acctId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	int acctId;
	
	@ManyToOne
	@JoinColumn(name="bankId")
	private Bank bank;
	
	@Column(name="acctType")
	String acctType;
	
	@Column(name="amount")
	double amount;

	public int getAcctId() {
		return acctId;
	}

	public String getAcctType() {
		return acctType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
