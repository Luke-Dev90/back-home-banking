package com.lchalela.banking.exceptions;

public class ListNotFoundCustomers extends RuntimeException{

	private static final long serialVersionUID = -4246822083576323595L;
	
	public ListNotFoundCustomers(String message) {
		super(message);
	}

}
