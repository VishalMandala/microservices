package com.vishal.exception;

import com.vishal.payload.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest req){
        HttpStatus status;
        String message = ex.getMessage();
        if (message != null && message.toLowerCase().contains("not found")) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ExceptionResponse response = new ExceptionResponse(
                message,
                req.getDescription(false),
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(response);
    }
}
