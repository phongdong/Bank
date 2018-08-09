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
@Table(name="account_customer")
@EntityListeners(AuditingEntityListener.class)

public class AccountCustomer 
{
	@Id
	@Column(name="acct_cust_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	int acctCustId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="acct_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Account account;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="cust_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Customer customer;
	
	public int getAcctCustId() {
		return acctCustId;
	}

	public void setAcctCustId(int acctCustId) {
		this.acctCustId = acctCustId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
