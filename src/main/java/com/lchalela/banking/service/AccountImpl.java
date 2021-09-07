package com.lchalela.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lchalela.banking.exceptions.AccountNotFoundID;
import com.lchalela.banking.exceptions.AccountNotFoundNumber;
import com.lchalela.banking.exceptions.ListAccountsNotFoundException;
import com.lchalela.banking.models.Account;
import com.lchalela.banking.repository.AccountRepository;

@Service
public class AccountImpl implements IAccountService{

	@Autowired
	private AccountRepository accountRepo;
	
	@Override
	public Account saveAccount(Account account) {
		return this.accountRepo.save(account);
	}

	@Override
	public Account getAccount(Long id)  {
		Account accountResult = this.accountRepo.findById(id).orElse(null);
		
		if(accountResult == null) {			
			 throw new AccountNotFoundID("Account not found with id: " + id);
		}else {
			return accountResult;
		}
		
	}

	@Override
	public Account getAccountByNumber(String account) {
		Account accountResult = this.accountRepo.getAccountByNumber(account);
		
		if(accountResult == null) {
			throw new AccountNotFoundNumber("Account not found with number: " + account);
		}else {
			return accountResult;
		}
	}

	@Override
	public Account updateAccountById(Long id,Account account) {
		
		Account accountResult = this.getAccount(id);
		
		accountResult.setAviablemoney(account.getAviablemoney());
		accountResult.setCustomer(account.getCustomer());
		accountResult.setNumber(account.getNumber());
		accountResult.setTransactions(account.getTransactions());
			
		return this.accountRepo.save(accountResult);
	}

	@Override
	public void deleteAccountById(Long id) {
		
		this.getAccount(id);
		
		this.accountRepo.deleteById(id);
	}

	@Override
	public List<Account> listAllAccount() {
		List<Account> listAccount = (List<Account>) this.accountRepo.findAll();
		
		if(listAccount.isEmpty()) {
			throw new ListAccountsNotFoundException("List account is empty");
		}else {			
			return listAccount;			
		}
		
	}

}
