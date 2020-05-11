package com.semantyca.yatt.controller.exception;

import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.error.ApiError;
import com.semantyca.yatt.dto.error.ErrorOutcome;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(-2)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String DEFAULT_ERROR_MESSAGE = "Malformed JSON request";

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        AbstractOutcome outcome = new ErrorOutcome();
        outcome.addPayload(new ApiError(HttpStatus.BAD_REQUEST, DEFAULT_ERROR_MESSAGE, ex));
        return new ResponseEntity(outcome, status);
    }

}

