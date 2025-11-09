package com.aep.doacaobooks.shared;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(ApplicationException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseError responseError = ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .status("error")
                .statusCode(HttpStatus.valueOf(exception.getStatusCode()).value())
                .error(HttpStatus.valueOf(exception.getStatusCode()).getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .details(exception.getDetails())
                .build();

        return handleExceptionInternal(exception, responseError, headers, HttpStatus.valueOf(exception.getStatusCode()),
                request);
    }
}
