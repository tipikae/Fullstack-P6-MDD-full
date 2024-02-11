package com.openclassrooms.mddapi.payload.response;

import lombok.Data;

/**
 * JsonWebToken response.
 * @author tipikae
 * @version 1.0.0
 */
@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;

    /**
     * Constructor.
     * @param token Token.
     * @param id User id.
     * @param username User username.
     */
    public JwtResponse(String token, Long id, String username) {
        this.token = token;
        this.id = id;
        this.username = username;
    }
}
