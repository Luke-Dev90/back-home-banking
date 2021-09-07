package com.lchalela.banking.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="accounts")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 185390685466249228L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String number;
	
	@JsonIgnoreProperties(value = {"account","hibernateLazyInitializer", "handler"})
	@OneToOne(mappedBy = "account", cascade = {CascadeType.ALL})
	private Customer customer;
	
	@NotNull
	private Double Aviablemoney;
	
	@JsonIgnoreProperties(value = {"account","hibernateLazyInitializer", "handler"})
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "account",cascade = {CascadeType.ALL})
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private List<Transaction> transactions;

	@JsonIgnoreProperties(value = {"account","hibernateLazyInitializer", "handler"})
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="moviments_id")
	private List<Transaction> movements;
	
	public void addMovements(Transaction transaction) {
		this.movements.add(transaction);
	}
	
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

	public List<Transaction> getMovements() {
		return movements;
	}

	public void setMovements(List<Transaction> movements) {
		this.movements = movements;
	}
	
	
	
}
