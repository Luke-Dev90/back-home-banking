package com.lchalela.banking.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.banking.models.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account,Long>{
	
	@Query("SELECT a FROM Account a WHERE a.number =?1")
	public Account getAccountByNumber(String account);
	
	
}
