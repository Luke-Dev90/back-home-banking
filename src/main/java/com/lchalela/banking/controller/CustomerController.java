package com.lchalela.banking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lchalela.banking.models.Customer;
import com.lchalela.banking.service.ICustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private ICustomerService customerRepository;
	private Map<String,Object> response = new HashMap<>();
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/all")
	public ResponseEntity<?> findAllCustomers(){
		response.clear();
		List<Customer> customers = this.customerRepository.listAllCustomers();
		response.put("message", "List found customers");
		response.put("customers", customers);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/get/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable String id){
		response.clear();
		Customer customer = Optional.of( this.customerRepository.getById(Long.parseLong(id)))
				.orElseThrow( () -> new NumberFormatException()) ;
		
		response.put("message", "found customer");
		response.put("customer",customer);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> saveCustomer(@Valid @RequestBody Customer customer){
		response.clear();
		Customer customerNew = this.customerRepository.registerCustomer(customer);
		response.put("message","Congratulations, you are registred.");
		response.put("customer",  customerNew);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable String id){
		response.clear();
		
		Long idUpdate = Optional.of( Long.parseLong(id)).orElseThrow( ()-> new NumberFormatException());
		
		this.customerRepository.updateById(idUpdate, customer);
		response.put("message", "customer updated successfully");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
}
