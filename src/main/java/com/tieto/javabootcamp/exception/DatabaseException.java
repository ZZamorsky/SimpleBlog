package com.tieto.javabootcamp.exception;

import java.io.IOException;

public class DatabaseException extends IOException {

	private static final long serialVersionUID = 4355621975617706325L;

	public DatabaseException() {
    }

    public DatabaseException(String message, Throwable cause) {
        super("Exception on Database side.", cause);
    }
}
