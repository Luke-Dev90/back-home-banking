package com.lchalela.banking.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{

	private Map<String,Object> response = new HashMap<>();
	
	// Exceptions Accounts
	
	@ExceptionHandler(AccountNotFoundID.class)
	public ResponseEntity<Object> accountNotFoundId(AccountNotFoundID ex, WebRequest request){
		response.clear();
		response.put("message", "Account not found with id " + ex.getLocalizedMessage());
		response.put("timestamp", LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Object> numberFormatException(NumberFormatException ex, WebRequest request){
		response.clear();
		response.put("message", "Id is invalid format, please enter valid format number");
		response.put("timestamp", LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccountNotFoundNumber.class)
	public ResponseEntity<Object> accountNotFoundNumber(AccountNotFoundNumber ex, WebRequest request){
		response.clear();
		response.put("message", "The account number not found, please enter account number correct");
		response.put("timestamp", LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	} 
	
	@ExceptionHandler(ListAccountsNotFoundException.class)
	public ResponseEntity<Object> listAccountsNotFoundException(ListAccountsNotFoundException ex, WebRequest request){
		response.clear();
		response.put("message", "Not found, list is empty");
		response.put("timestamp",LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	
	//Exceptions Cutomers
	
	@ExceptionHandler(CustomerNotFoundId.class)
	public ResponseEntity<Object> customerNotFoundId(CustomerNotFoundId ex, WebRequest request){
		response.clear();
		response.put("message", "Not found customer with id: " + ex.getLocalizedMessage());
		response.put("timestap", LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ListNotFoundCustomers.class)
	public ResponseEntity<Object> listNotFoundCustomers(ListNotFoundCustomers ex, WebRequest request){
		response.clear();
		response.put("message", "Not found list coustomers");
		response.put("timestamp", LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerNotFoundExcepetion.class)
	public ResponseEntity<Object> customerNotFoundExcepetion(CustomerNotFoundExcepetion ex, WebRequest request){
		response.clear();
		response.put("message", "Customer not found");
		response.put("timestamp", LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	//Exception Transaction
	
	@ExceptionHandler(ListTransactionNotFoundException.class)
	public ResponseEntity<Object> listTransactionNotFoundException(ListTransactionNotFoundException ex, WebRequest request){
		response.clear();
		response.put("message", "Not found list transactions");
		response.put("timestamp", LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InsufficientFundsException.class)
	public ResponseEntity<Object> insufficientFundsException(InsufficientFundsException ex,WebRequest request){
		response.clear();
		response.put("message", "Insufficiente funds, please check the amount");
		response.put("timestap", LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		response.clear();
		List<String> errors = ex.getAllErrors()
				.stream()
				.map( err -> "Field error: " +ex.getFieldError() + " " + ex.getLocalizedMessage())
				.collect(Collectors.toList());
		response.put("errors", errors);
		response.put("status",status);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	

}
