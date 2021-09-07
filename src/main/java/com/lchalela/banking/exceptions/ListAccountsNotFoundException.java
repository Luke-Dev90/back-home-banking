package com.lchalela.banking.exceptions;

public class ListAccountsNotFoundException  extends RuntimeException{
	
	
	private static final long serialVersionUID = 5314277519857346107L;

	public ListAccountsNotFoundException(String message) {
		super(message);
	}

}
