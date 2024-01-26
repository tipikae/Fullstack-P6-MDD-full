package com.openclassrooms.mddapi.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mddapi.payload.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Authentication exception handler.
 * @author tipikae
 * @version 1.0.0
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    /**
     * Intercept the rejected request.
     * @param request Rejected request.
     * @param response Returned response.
     * @param authException Thrown exception.
     * @throws IOException thrown updating the response.
     * @throws ServletException thrown an error occurred servlet side.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        LOGGER.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ErrorResponse errorResponse = new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
