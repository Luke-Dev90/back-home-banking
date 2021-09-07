package com.lchalela.banking.service;

import java.util.List;

import com.lchalela.banking.models.Account;

public interface IAccountService {
	
	public List<Account> listAllAccount();

	public Account saveAccount(Account account);
	
	public Account getAccount(Long id);
	
	public Account getAccountByNumber(String account);
	
	public Account AccountByIdupdate(Long id,Account account) ;
	
	public void AccountByIdDelete(Long id);
	
	
}
