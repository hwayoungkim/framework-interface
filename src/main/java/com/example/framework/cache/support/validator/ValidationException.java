package com.example.framework.cache.support.validator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Validation Exception
 *
 * @date 2013. 5. 8. 오후 3:15:06
 * @version $Id$
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "validation failed.")
public class ValidationException extends RuntimeException {

	/**
	 * The Constructor
	 *
	 */
	public ValidationException() {
		super();
	}
	
	/**
	 * The Constructor
	 * 
	 * @param message Message
	 */
	public ValidationException(String message) {
		super(message);
	}
	
}
