package com.example.demo.exception;

public class ProductIdNotFoundException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductIdNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductIdNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ProductIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ProductIdNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ProductIdNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
