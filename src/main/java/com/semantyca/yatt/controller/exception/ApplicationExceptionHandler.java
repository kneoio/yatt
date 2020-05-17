package com.semantyca.yatt.controller.exception;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dto.IOutcome;
import com.semantyca.yatt.dto.OutcomeType;
import com.semantyca.yatt.dto.constant.PayloadType;
import com.semantyca.yatt.dto.error.ApplicationError;
import com.semantyca.yatt.dto.error.ErrorOutcome;
import com.semantyca.yatt.service.exception.DocumentAccessException;
import com.semantyca.yatt.service.exception.DocumentNotFoundException;
import com.semantyca.yatt.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Order(-3)
@ControllerAdvice
public class ApplicationExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);


    @ExceptionHandler(value = { AccessDeniedException.class })
    public @ResponseBody
    ResponseEntity<IOutcome> serverErrorHandler(HttpServletRequest request, AccessDeniedException e) {
        String errorId = "aderr#" + StringUtil.getRndText(20);
        logger.error("Error tracking Id: {}", errorId);
        ErrorOutcome outcome = new ErrorOutcome();
        outcome.setIdentifier(errorId);
        outcome.setPageName(HttpStatus.FORBIDDEN.toString());
        outcome.setTitle("Access denied");
        outcome.addPayload(new ApplicationError(errorId));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(outcome);
    }

    @ExceptionHandler(value = { DocumentNotFoundException.class })
    public @ResponseBody
    ResponseEntity<IOutcome> documentErrorHandler(HttpServletRequest request, DocumentNotFoundException e) {
        String errorId = "dnferr#" + StringUtil.getRndText(20);
        logger.error("Error tracking Id: {}, dev message: {}:", errorId, e.getDeveloperMessage());
        ErrorOutcome outcome = new ErrorOutcome();
        outcome.setIdentifier(errorId);
        outcome.setPageName(HttpStatus.NOT_FOUND.toString());
        outcome.setTitle("Document not found");
        outcome.addPayload(new ApplicationError(errorId));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(outcome);
    }

    @ExceptionHandler(value = { DocumentAccessException.class })
    public @ResponseBody
    ResponseEntity<IOutcome> documentErrorHandler(HttpServletRequest request, DocumentAccessException e) {
        String errorId = "daerr#" + StringUtil.getRndText(20);
        logger.error("Error tracking Id: {}, dev message: {}:", errorId, e.getDeveloperMessage());

        ErrorOutcome outcome = new ErrorOutcome();
        outcome.setIdentifier(errorId);
        outcome.setPageName(HttpStatus.FORBIDDEN.toString());
        outcome.setType(OutcomeType.SOFT_ERROR);
        outcome.setTitle("Document access restricted");
        if (EnvConst.DEV_MODE) {
            outcome.addPayload(PayloadType.EXCEPTION, e.getDeveloperMessage());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(outcome);
    }
}
