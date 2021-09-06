package com.lchalela.banking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.banking.models.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account,Long>{
	
	public Account getAccountByNumber(String account);
	
	public Account updateAccountById(Long id);
	
}
