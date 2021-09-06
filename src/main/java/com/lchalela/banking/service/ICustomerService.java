package com.lchalela.banking.service;

import java.util.List;

import com.lchalela.banking.models.Customer;

public interface ICustomerService {

	
	public Customer registerCustomer(Customer customer);
	
	public Customer getById(Long id);
	
	public Customer updateById(Long id,Customer customer);
	
	public List<Customer> listAllCustomers();
}
