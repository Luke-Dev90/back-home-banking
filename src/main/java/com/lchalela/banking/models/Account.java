package com.lchalela.banking.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="accounts")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 185390685466249228L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String number;
	
	@OneToOne(mappedBy = "account")
	private Customer customer;
	
	@NotNull
	private Double Aviablemoney;
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "account")
	private List<Transaction> transactions;

	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getAviablemoney() {
		return Aviablemoney;
	}

	public void setAviablemoney(Double aviablemoney) {
		Aviablemoney = aviablemoney;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	
}
