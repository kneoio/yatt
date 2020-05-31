package com.semantyca.yatt.controller.exception;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dto.IOutcome;
import com.semantyca.yatt.dto.OutcomeType;
import com.semantyca.yatt.dto.constant.PayloadType;
import com.semantyca.yatt.dto.error.ApplicationError;
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

@Order(-1)
@ControllerAdvice
public class ServerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ServerExceptionHandler.class);

    @ExceptionHandler(value = {HttpMessageConversionException.class})
    protected ResponseEntity<Object> messageConversionException(HttpMessageConversionException ex) {
        String errorId = "mcerr#" + StringUtil.getRndText(20);
        IOutcome outcome = new ErrorOutcome()
                .setIdentifier(errorId)
                .setType(OutcomeType.HARD_ERROR)
                .setPageName(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .setTitle("Message conversion error");
        if (EnvConst.DEV_MODE) {
            ex.printStackTrace();
            ApplicationError error = new ApplicationError(ex.getMessage());
            outcome.addPayload(PayloadType.EXCEPTION, error);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(outcome);


    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> generalHandleException(Exception exception) {
        String errorId = "err#" + StringUtil.getRndText(20);
        exception.printStackTrace();
        IOutcome outcome = new ErrorOutcome()
                .setIdentifier(errorId)
                .setType(OutcomeType.HARD_ERROR)
                .setPageName(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .setTitle("Unknown internal server error");
        if (EnvConst.DEV_MODE) {
            exception.printStackTrace();
            ApplicationError error = new ApplicationError(exception.getMessage());
            outcome.addPayload(PayloadType.EXCEPTION, error);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(outcome);
    }


}
