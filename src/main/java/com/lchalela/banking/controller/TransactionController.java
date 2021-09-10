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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lchalela.banking.models.Transaction;
import com.lchalela.banking.service.ITransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	
	@Autowired
	private ITransactionService transactionService;
	private Map<String,Object> response = new HashMap<>();
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/all")  // for admins
	public ResponseEntity<?> listAllTransactions(){
		response.clear();
		List<Transaction> transactions = this.transactionService.listAllTransactions();
		response.put("transactions", transactions);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getTransactionId(@PathVariable String id){
		response.clear();
		
		Transaction transaction =  Optional.of(this.transactionService.getById(Long.parseLong(id)))
				.orElseThrow( ()-> new NumberFormatException());
		
		response.put("message", "transaction found successfully");
		response.put("transaction", transaction);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/save")
	public ResponseEntity<?> saveTransaction(@Valid @RequestBody Transaction transaction){
		Transaction transactionNew = this.transactionService.saveTransaction(transaction);
		response.clear();
		response.put("transaction", transactionNew);
		response.put("message", "sent transaction successfully");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
}
