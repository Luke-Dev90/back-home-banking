package com.lchalela.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly=true)
	public Account getAccount(Long id)  {
		Account accountResult = this.accountRepo.findById(id).orElse(null);
		
		if(accountResult == null) {			
			 throw new AccountNotFoundID("Account not found with id: " + id);
		}else {
			return accountResult;
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public Account getAccountByNumber(String account) {
		Account accountResult = this.accountRepo.getAccountByNumber(account);
		
		if(accountResult == null) {
			throw new AccountNotFoundNumber("Account not found with number: " + account);
		}else {
			return accountResult;
		}
	}

	@Override
	@Transactional
	public Account AccountByIdupdate(Long id,Account account) {
		Account accountUpdate = null;
		Optional<Account> accountResult = Optional.of(this.getAccount(id));
		
		if(accountResult.isPresent()) {
			accountUpdate = accountResult.get();
			accountUpdate.setAviablemoney(account.getAviablemoney());
			accountUpdate.setCustomer(account.getCustomer());
			accountUpdate.setNumber(account.getNumber());
			accountUpdate.setTransactions(account.getTransactions());
			accountUpdate.setMovements(account.getMovements());
			
			
			accountUpdate = this.accountRepo.save(accountUpdate);
		}			
		
		return accountUpdate;
	}

	@Override
	@Transactional
	public void AccountByIdDelete(Long id) {
		
		this.getAccount(id);
		
		this.accountRepo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Account> listAllAccount() {
		List<Account> listAccount = (List<Account>) this.accountRepo.findAll();
		
		if(listAccount.isEmpty()) {
			throw new ListAccountsNotFoundException("List account is empty");
		}else {			
			return listAccount;			
		}
		
	}

}
