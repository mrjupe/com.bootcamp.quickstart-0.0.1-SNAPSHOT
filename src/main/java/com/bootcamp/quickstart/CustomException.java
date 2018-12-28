package com.bootcamp.quickstart;

public class CustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8501412577072954003L;

	public CustomException(String errorMessage) {
		super(errorMessage);
	}
}
