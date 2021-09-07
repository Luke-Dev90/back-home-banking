package com.lchalela.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.banking.models.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long>{

	
	//add later query filter transactions by id account.
	@Query("SELECT t FROM Transaction t WHERE t.id =?1")
	public List<Transaction> getbyAccountId(Long id);
}
