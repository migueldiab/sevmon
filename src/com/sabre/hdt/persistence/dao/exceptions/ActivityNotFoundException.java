package com.sabre.hdt.persistence.dao.exceptions;

public class ActivityNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ActivityNotFoundException(){
		
	}
	
	public ActivityNotFoundException(String message){
		super(message);
	}
	
	public ActivityNotFoundException(Throwable t)
	{
		super(t);
	}

	public ActivityNotFoundException(String msg, Throwable t){
		super(msg, t);
	}

}
