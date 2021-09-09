package com.lchalela.banking.exceptions;

public class CustomerNotFoundExcepetion extends RuntimeException{

	private static final long serialVersionUID = 21208400824589988L;

	public CustomerNotFoundExcepetion(String message) {
		super(message);
	}
}
