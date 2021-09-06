package com.lchalela.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lchalela.banking.models.Account;
import com.lchalela.banking.models.Transaction;
import com.lchalela.banking.repository.TransactionRepository;

@Service
public class TransactionImpl implements ITransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private IAccountService accountService;
	
	@Override
	public Transaction saveTransaction(Transaction transaction) {
		
		//find sender account sender
		Account accountSender = this.accountService.getAccountByNumber(transaction.getSenderNumber());
		//find destinaty isExists
		Account accountDestinaty = this.accountService.getAccountByNumber(transaction.getSenderNumber());
		

		  //if money > amount_send OK
		if(accountSender.getAviablemoney() < transaction.getAmount()) {
			return null;  // retun exception FOUND INSUFICIENT
		}
		
		Transaction transactionNew = this.saveTransaction(transaction);
		
		
		Double aviableMoneyNewSender = accountSender.getAviablemoney() - transaction.getAmount();  
		accountSender.setAviablemoney(aviableMoneyNewSender);
		accountSender.addTransaction(transactionNew);
		
		this.accountService.updateAccountById(accountSender.getId(),accountSender);
		
		Double aviableMoneyNewDestinatary = accountDestinaty.getAviablemoney() + transaction.getAmount();
		accountDestinaty.setAviablemoney(aviableMoneyNewDestinatary);
		accountDestinaty.addTransaction(transactionNew);
		
		this.accountService.updateAccountById(accountSender.getId(),accountSender);
		
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

}
