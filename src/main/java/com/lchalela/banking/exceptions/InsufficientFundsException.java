package com.lchalela.banking.exceptions;

public class InsufficientFundsException extends RuntimeException{

	private static final long serialVersionUID = -7056913030799354719L;

	public InsufficientFundsException(String message) {
		super(message);
	}
}
