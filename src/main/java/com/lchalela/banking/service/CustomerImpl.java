package com.lchalela.banking.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lchalela.banking.exceptions.CustomerNotFoundExcepetion;
import com.lchalela.banking.exceptions.CustomerNotFoundId;
import com.lchalela.banking.exceptions.ListNotFoundCustomers;
import com.lchalela.banking.models.Account;
import com.lchalela.banking.models.Customer;
import com.lchalela.banking.models.Role;
import com.lchalela.banking.repository.CustomerRepository;


@Service
public class CustomerImpl implements ICustomerService ,UserDetailsService {

	
	
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
		
		Role role = new Role();
		role.setRole("ROLE_USERNAME");
		
		customerNew.addRole(role);
		customerNew.setPassword(customer.getPassword());
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
		customerResult.setDni(customer.getDni());
		customerResult.setEnabled(customer.getEnabled());
		this.customerRepository.save(customerResult);

		return customerResult;
	}

	@Override
	public List<Customer> listAllCustomers() {
		List<Customer> customers = (List<Customer>) this.customerRepository.findAll();
		
		if (customers.isEmpty()) {
			throw  new ListNotFoundCustomers("List not found");
		} else {
			return customers;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = this.customerRepository.findByUsername(username);
		
		if(customer == null) {
			throw new CustomerNotFoundExcepetion("No fount customer");
		}
		
		List<GrantedAuthority> authorities = customer.getRoles()
				.stream()
				.map( role -> new SimpleGrantedAuthority(role.getRole() ))
				.collect(Collectors.toList());
					
		return new User(customer.getUsername(),customer.getPassword(),customer.getEnabled(),true,true,true,authorities);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Customer findByUsername(String username) {
		return customerRepository.findByUsername(username);
	}

}
