package com.semantyca.yatt.controller;

import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.OutcomeType;
import com.semantyca.yatt.dto.error.ApiError;
import com.semantyca.yatt.dto.error.ErrorOutcome;
import com.semantyca.yatt.dto.error.ValidationError;
import com.semantyca.yatt.util.StringUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String DEFAULT_ERROR_MESSAGE = "Malformed JSON request";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        AbstractOutcome outcome = new ErrorOutcome().setPayload(new ApiError(HttpStatus.BAD_REQUEST, DEFAULT_ERROR_MESSAGE, ex));
        return new ResponseEntity(outcome, status);
    }

    @ExceptionHandler(value= { IllegalArgumentException.class, IllegalStateException.class, Exception.class })
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorId = "verr#" + StringUtil.getRndText(20);
        ex.printStackTrace();
        if (ex instanceof MethodArgumentNotValidException){
            ValidationError error = new ValidationError(ex.getMessage());
            for (final FieldError error1 : ex.getBindingResult().getFieldErrors()) {
                error.addError(error1.getField(),error1.getDefaultMessage());
            }
            for (final ObjectError error2 : ex.getBindingResult().getGlobalErrors()) {
                error.addError(error2.getObjectName(),error2.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorOutcome()
                            .setIdentifier(errorId)
                            .setTitle(error.getMessage())
                            .setPayload(error)
                            .setType(OutcomeType.VALIDATION_ERROR));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorOutcome()
                            .setIdentifier(errorId)
                            .setPageName(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                            .setTitle("Internal server error"));
        }
    }


}
