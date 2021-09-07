package com.lchalela.banking.exceptions;

public class AccountNotFoundID extends RuntimeException{
	
	private static final long serialVersionUID = 3702999255463228580L;

	public AccountNotFoundID(String message) {
		super(message);
	}	

}
