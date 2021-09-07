package com.lchalela.banking.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lchalela.banking.exceptions.CustomerNotFoundId;
import com.lchalela.banking.exceptions.ListNotFoundCustomers;
import com.lchalela.banking.models.Account;
import com.lchalela.banking.models.Customer;
import com.lchalela.banking.repository.CustomerRepository;

@Service
public class CustomerImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private IAccountService accountService;

	@Override
	public Customer registerCustomer(Customer customer) {

		Account accountNew = new Account();
		accountNew.setAviablemoney(0d);
		accountNew.setNumber(UUID.randomUUID().toString());
		String numberAcount = accountNew.getNumber();

		this.accountService.saveAccount(accountNew);

		accountNew = this.accountService.getAccountByNumber(numberAcount);

		Customer customerNew = customer;
		customerNew.setAccount(accountNew);

		this.customerRepository.save(customerNew);

		return customerNew;
	}

	@Override
	public Customer getById(Long id) {
		Customer customer = this.customerRepository.findById(id).orElse(null);

		if (customer == null) {
			throw new CustomerNotFoundId("Customer not fount with id : " + id);
		} else {
			return customer;
		}
	}

	@Override
	public Customer updateById(Long id, Customer customer) {

		Customer customerResult = this.getById(id);

		customerResult.setAccount(customer.getAccount());
		customerResult.setEmail(customer.getEmail());
		customerResult.setLastname(customer.getLastname());
		customerResult.setName(customer.getName());
		customerResult.setPassword(customer.getPassword());

		this.customerRepository.save(customerResult);

		return customerResult;
	}

	@Override
	public List<Customer> listAllCustomers() {
		List<Customer> customers = (List<Customer>) this.customerRepository.findAll();
		if (customers.isEmpty()) {
			throw new ListNotFoundCustomers("List not found");
		} else {
			return customers;
		}
	}

}
