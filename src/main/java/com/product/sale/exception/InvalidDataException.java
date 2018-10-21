package com.product.sale.exception;

/**
 * @author pradeep.a.nair
 * Checked Exception class to handle Invalid data scenarios
 *
 */
public class InvalidDataException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public InvalidDataException(String message) {
		super(message);

	}

}
