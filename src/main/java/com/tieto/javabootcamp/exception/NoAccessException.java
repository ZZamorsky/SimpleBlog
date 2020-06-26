package com.tieto.javabootcamp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoAccessException extends RuntimeException {
	
	private static final long serialVersionUID = 1777448193745829824L;

	public NoAccessException(String message) {
			super(message);

	}
}
