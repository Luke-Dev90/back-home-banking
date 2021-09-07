package com.lchalela.banking.exceptions;

public class AccountNotFoundNumber extends RuntimeException {

	private static final long serialVersionUID = 6489084361328678743L;

	public AccountNotFoundNumber(String message) {
		super(message);
	}
}
