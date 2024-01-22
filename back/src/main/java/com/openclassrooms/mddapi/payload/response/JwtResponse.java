package com.openclassrooms.mddapi.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;

    public JwtResponse(String token, Long id, String username) {
        this.token = token;
        this.id = id;
        this.username = username;
    }
}
