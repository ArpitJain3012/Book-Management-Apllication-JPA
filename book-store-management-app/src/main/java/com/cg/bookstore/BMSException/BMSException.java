package com.cg.bookstore.BMSException;

public class BMSException extends Exception{
	private static final long serialVersionUID = 1L;

	public BMSException(String errormessage)
	{
		super(errormessage);
	}

}

