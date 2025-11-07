package com.ems.management;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.ems.management.dto.response.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex, WebRequest request, HttpStatus status) {
        ApiError error = ApiError.of(ex.getMessage(), status.name(),
                request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, WebRequest request, HttpStatus status) {
        ApiError error = ApiError.of("Internal server error", status.name(),
                request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(error, status);
    }
}

