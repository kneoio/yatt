package com.semantyca.yatt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semantyca.yatt.dto.error.ApplicationError;
import com.semantyca.yatt.dto.error.ErrorOutcome;
import com.semantyca.yatt.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationExceptionHandler extends AccessDeniedHandlerImpl implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationExceptionHandler.class);

    private ObjectMapper mapper;

    public AuthenticationExceptionHandler() {
    }

    public AuthenticationExceptionHandler(ObjectMapper jacksonObjectMapper) {
        this.mapper = jacksonObjectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String errorId = "aderr#" + StringUtil.getRndText(20);
        logger.error("Error tracking Id: {}", errorId);
        ErrorOutcome outcome = new ErrorOutcome();
        outcome.setIdentifier(errorId);
        outcome.setTitle(e.getMessage());
        outcome.addPayload(new ApplicationError(errorId));
        String val = mapper.writeValueAsString(outcome);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(val);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        super.handle(request, response, e);
        logger.error("authetication excpetion handling ...");
    }
}
