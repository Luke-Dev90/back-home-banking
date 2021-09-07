package com.lchalela.banking.exceptions;

public class CustomerNotFoundId extends RuntimeException{

	private static final long serialVersionUID = 1887924617468461615L;
	
	public CustomerNotFoundId(String message) {
		super(message);
	}

}
