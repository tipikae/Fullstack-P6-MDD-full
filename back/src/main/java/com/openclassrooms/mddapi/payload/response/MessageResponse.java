package com.openclassrooms.mddapi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Message response.
 * @author tipikae
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class MessageResponse {

    private String message;
}
