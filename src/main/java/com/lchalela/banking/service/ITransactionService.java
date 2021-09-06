package com.lchalela.banking.service;

import java.util.List;

import com.lchalela.banking.models.Transaction;

public interface ITransactionService {
	
	public Transaction saveTransaction(Transaction transaction);
	
	public List<Transaction> getBySender();
	
	public List<Transaction> getByRecived();
	
	public List<Transaction> byAccountId(Long id);
	
}
