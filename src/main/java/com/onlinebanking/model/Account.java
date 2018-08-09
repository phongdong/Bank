package com.onlinebanking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="account")
@EntityListeners(AuditingEntityListener.class)

public class Account
{
	@Id
	@Column(name="acct_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	int acctId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="bank_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Bank bank;
	
	@Column(name="acct_type")
	String acctType;
	
	@Column(name="amount")
	double amount;

	public int getAcctId() {
		return acctId;
	}

	public void setAcctId(int acctId) {
		this.acctId = acctId;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
