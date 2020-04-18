package com.centime.person.infrastructure.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Global Exception Handler for Rest APIs
 *
 * @author susheel
 */
@ControllerAdvice
@EnableWebMvc
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handler for Bad Requests
     *
     * @param ex Exception handled
     * @param request WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler({ IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleBadRequests(IllegalArgumentException ex, final WebRequest request) {

        final List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());

        final ErrorResponse appError = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST).errors(errors).build();

        return handleExceptionInternal(ex, appError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {

        final List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());

        final ErrorResponse appError = ErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errors(errors).build();

        return handleExceptionInternal(ex, appError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
