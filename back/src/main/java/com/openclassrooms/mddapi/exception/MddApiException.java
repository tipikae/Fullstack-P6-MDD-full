package com.openclassrooms.mddapi.exception;

/**
 * Parent application exception.
 * @author tipikae
 * @version 1.0.0
 */
public class MddApiException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public MddApiException(String message) {
        super(message);
    }
}
