package com.openclassrooms.mddapi.exception;

/**
 * BadRequest exception.
 * @author tipikae
 * @version 1.0.0
 */
public class BadRequestException extends MddApiException {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
