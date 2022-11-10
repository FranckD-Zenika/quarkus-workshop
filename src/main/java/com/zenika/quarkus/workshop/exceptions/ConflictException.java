package com.zenika.quarkus.workshop.exceptions;

import javax.ws.rs.ClientErrorException;

import java.io.Serial;

import static javax.ws.rs.core.Response.Status.CONFLICT;

public class ConflictException extends ClientErrorException {

    @Serial
    private static final long serialVersionUID = -4773207978414172055L;

    public ConflictException() {
        super(CONFLICT);
    }

    public ConflictException(String message) {
        super(message, CONFLICT);
    }

    public ConflictException(Throwable cause) {
        super(CONFLICT, cause);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, CONFLICT, cause);
    }

}
