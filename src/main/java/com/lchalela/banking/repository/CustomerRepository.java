package com.lchalela.banking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.banking.models.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

	
}
