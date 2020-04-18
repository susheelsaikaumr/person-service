package com.centime.person.infrastructure.web;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Object representing an Error to be sent to the API caller incase of an Error.
 *
 * @author susheel
 */
@Data
@Builder
@SuppressWarnings({"checkstyle:javadocmethod"})
class ErrorResponse {

    private final HttpStatus status;
    private final List<String> errors;

    private String description;
}