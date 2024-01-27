package com.openclassrooms.mddapi.payload.response;

import lombok.Data;

import java.util.Date;

/**
 * Error response POJO.
 * @author tipikae
 * @version 1.0.0
 */
@Data
public class ErrorResponse {

    private int status;
    private String message;
    private Date timestamp;

    /**
     * Constructor.
     * @param status Error code.
     * @param message Error message.
     */
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
