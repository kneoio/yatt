package com.semantyca.yatt.controller.exception;

import com.semantyca.juka.exception.ExceptionUtil;
import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dto.IOutcome;
import com.semantyca.yatt.dto.OutcomeType;
import com.semantyca.yatt.dto.constant.PayloadType;
import com.semantyca.yatt.dto.error.ApplicationError;
import com.semantyca.yatt.dto.error.ErrorOutcome;
import com.semantyca.yatt.dto.error.ValidationError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@Order(-2)
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String DEFAULT_ERROR_MESSAGE = "Malformed JSON request";
    private static final String ERROR_MNEMONIC = "api";


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorId = ExceptionUtil.getErrorCode("v");
        IOutcome outcome = new ErrorOutcome()
                .setIdentifier(errorId)
                .setType(OutcomeType.VALIDATION_ERROR)
                .setPageName(HttpStatus.BAD_REQUEST.toString())
                .setTitle(DEFAULT_ERROR_MESSAGE);
        ValidationError error = new ValidationError();
        if (EnvConst.DEV_MODE) {
            BindingResult result = ex.getBindingResult();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                error.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            }
            outcome.addPayload(PayloadType.EXCEPTION, error);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outcome);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorId = ExceptionUtil.getErrorCode(ERROR_MNEMONIC);
        IOutcome outcome = new ErrorOutcome()
                .setIdentifier(errorId)
                .setType(OutcomeType.HARD_ERROR)
                .setPageName(HttpStatus.BAD_REQUEST.toString())
                .setTitle(DEFAULT_ERROR_MESSAGE);
        if (EnvConst.DEV_MODE) {
            ex.printStackTrace();
            ApplicationError error = new ApplicationError(ex.getMessage());
            outcome.addPayload(PayloadType.EXCEPTION, error);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outcome);

    }

}

