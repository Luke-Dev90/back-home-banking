package com.lchalela.banking.exceptions;



public class ListTransactionNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1406561811094086237L;

	public ListTransactionNotFoundException(String message) {
		super(message);
	}
}
