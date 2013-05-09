package com.example.framework.cache.support.validator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Service Exception
 *
 * @date 2013. 5. 8. 오후 3:15:06
 * @version $Id$
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "server error.")
public class ServiceException extends RuntimeException {

	/**
	 * The Constructor
	 *
	 */
	public ServiceException() {
		super();
	}
	
	/**
	 * The Constructor
	 * 
	 * @param message Message
	 */
	public ServiceException(String message) {
		super(message);
	}
	
}
