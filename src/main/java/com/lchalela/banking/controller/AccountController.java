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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lchalela.banking.models.Account;
import com.lchalela.banking.service.IAccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private IAccountService accountService;
	private	Map<String,Object> response = new HashMap<>();
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/all")
	public ResponseEntity<?> listAllAccounts(){
		response.clear();
		List<Account> list = this.accountService.listAllAccount();	
		response.put("message", "The list is found successfull");
		response.put("list", list);	
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable String id){
		response.clear();
		Account account = Optional.of(this.accountService.getAccount(Long.parseLong(id)))
				.orElseThrow( () -> new NumberFormatException());
		response.put("message", "Welcome, remember, never share personal information and password ");
		response.put("account", account);
	
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public ResponseEntity<?> saveAccount(@Valid @RequestBody Account account){
		response.clear();
		this.accountService.saveAccount(account);	
		response.put("message", "account created successfully");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateAccountById(@Valid @RequestBody Account account, @PathVariable String id){
		response.clear();
		Account accountUpdate = Optional.of(this.accountService.AccountByIdupdate(Long.parseLong(id), account))
				.orElseThrow(  () -> new NumberFormatException() );
		response.put("message", "account updated successfull");
		response.put("account", accountUpdate);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> AccountByIdDelete(@PathVariable String id){
		response.clear();
		
		Long idDelete = Optional.of(Long.parseLong(id))
				.orElseThrow( () -> new NumberFormatException());
		
		this.accountService.AccountByIdDelete(idDelete);
		response.put("message", "deleted account");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}
