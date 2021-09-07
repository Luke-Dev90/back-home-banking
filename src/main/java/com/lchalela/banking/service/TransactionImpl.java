package com.lchalela.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lchalela.banking.exceptions.InsufficientFundsException;
import com.lchalela.banking.exceptions.ListTransactionNotFoundException;
import com.lchalela.banking.models.Account;
import com.lchalela.banking.models.Transaction;
import com.lchalela.banking.repository.TransactionRepository;

@Service
public class TransactionImpl implements ITransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private IAccountService accountService;
	
	public List<Transaction> listAllTransactions(){
		List<Transaction> transactions =(List<Transaction>) this.transactionRepository.findAll();
		
		if(transactions.isEmpty()) {
			throw new ListTransactionNotFoundException("List transactions not founds");
		}
		return transactions;
	}
	
	@Override
	@Transactional
	public Transaction saveTransaction(Transaction transaction) {
		
		Account accountSender = this.accountService.getAccountByNumber(transaction.getSenderNumber());

		Account accountDestinaty = this.accountService.getAccountByNumber(transaction.getDestinyNumber());
		
		if(accountSender.getAviablemoney() < transaction.getAmount()) {
			throw new InsufficientFundsException("Insuficient funds, please check the amount");
		}
		
		Transaction transactionNew = this.transactionRepository.save(transaction);
		
		
		
		accountSender.addMovements(transactionNew);
		accountDestinaty.addMovements(transactionNew);
		
		Double aviableMoneyNewSender = accountSender.getAviablemoney() - transaction.getAmount();  
		accountSender.setAviablemoney(aviableMoneyNewSender);
		

		this.accountService.AccountByIdupdate(accountSender.getId(),accountSender);
		
		Double aviableMoneyNewDestinatary = accountDestinaty.getAviablemoney() + transaction.getAmount();
		accountDestinaty.setAviablemoney(aviableMoneyNewDestinatary);
		
		this.accountService.AccountByIdupdate(accountSender.getId(),accountSender);
		
		return transactionNew;
	}

	@Override
	public List<Transaction> getBySender() {
		List<Transaction> transactions = (List<Transaction>) this.transactionRepository.findAll();
		if(transactions.isEmpty()) {
			return null; // create new exceptions
		}else {			
			return transactions;
		}
	}

	@Override
	public List<Transaction> getByRecived() {
		//implements list by transactions recibed.
		return null;
	}

	@Override
	public List<Transaction> byAccountId(Long id) {
		// implements list by transactions where id account = id transaction.
		return null;
	}

	@Override
	public Transaction getById(Long id) {
			Transaction transaction = this.transactionRepository.findById(id).orElse(null);
			if(transaction == null) {
				// throw new Exception
			}
		return transaction;
	}

}
