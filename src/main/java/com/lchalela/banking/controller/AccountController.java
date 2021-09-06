package com.lchalela.banking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/all")
	public ResponseEntity<?> listAllAccounts(){
		List<Account> list = this.accountService.listAllAccount();	
		response.put("message", "The list is found successfull");
		response.put("list", list);	
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable String id){
		
		Account account = this.accountService.getAccount(Long.parseLong(id));
		response.put("message", "Welcome, remember, never share personal information and password ");
		response.put("account", account);
	
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveAccount(@Valid @RequestBody Account account){
		this.accountService.saveAccount(account);	
		response.put("message", "account created successfully");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateAccountById(@Valid @RequestBody Account account, @PathVariable String id){
		
		Account accountUpdate = this.accountService.updateAccountById(Long.parseLong(id), account);
		response.put("message", "account updated successfull");
		response.put("account", accountUpdate);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAccountById(@PathVariable String id){
		this.accountService.deleteAccountById(Long.parseLong(id));
		response.put("message", "deleted account");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}
