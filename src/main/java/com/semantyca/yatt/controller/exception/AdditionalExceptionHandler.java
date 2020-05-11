package com.semantyca.yatt.controller.exception;

import com.semantyca.yatt.dto.error.ErrorOutcome;
import com.semantyca.yatt.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(-3)
@ControllerAdvice
public class AdditionalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(AdditionalExceptionHandler.class);

    @ExceptionHandler(value = {HttpMessageConversionException.class})
    protected ResponseEntity<Object> messageConversionException(HttpMessageConversionException ex) {
        String errorId = "mcerr#" + StringUtil.getRndText(20);
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorOutcome()
                        .setIdentifier(errorId)
                        .setPageName(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .setTitle("Message conversion error"));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> generalHandleException(Exception exception) {
        String errorId = "err#" + StringUtil.getRndText(20);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorOutcome()
                        .setIdentifier(errorId)
                        .setPageName(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .setTitle("Unknown internal server error"));
    }


}
