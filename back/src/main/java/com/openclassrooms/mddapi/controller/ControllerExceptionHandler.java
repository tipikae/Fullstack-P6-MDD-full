package com.openclassrooms.mddapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mddapi.exception.AlreadyExistsException;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.payload.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller exception handler.
 * @author tipikae
 * @version  1.0.0
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * BadRequestException handler.
     * @param e exception.
     * @return ErrorResponse
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    ErrorResponse exceptionHandler(BadRequestException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * ConstraintViolationException handler.
     * @param e exception.
     * @return ErrorResponse
     * @throws JsonProcessingException thrown when an error occurred during mapping.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ErrorResponse exceptionHandler(ConstraintViolationException e) throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().forEach(error -> errors.put(error.getRootBeanClass().getName(), error.getMessage()));
        ObjectMapper mapper = new ObjectMapper();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), mapper.writeValueAsString(errors));
    }

    /**
     * MethodArgumentNotValidException handler.
     * @param e exception
     * @return ErrorResponse
     * @throws JsonProcessingException thrown when an error occurred during mapping.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ErrorResponse exceptionHandler(MethodArgumentNotValidException e) throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        ObjectMapper mapper = new ObjectMapper();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), mapper.writeValueAsString(errors));
    }

    /**
     * NotFoundException handler.
     * @param e exception
     * @return ErrorResponse
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    ErrorResponse exceptionHandler(NotFoundException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    /**
     * AlreadyExistsException handler.
     * @param e exception
     * @return ErrorResponse
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyExistsException.class)
    ErrorResponse exceptionHandler(AlreadyExistsException e) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }
}
