package com.semantyca.yatt.dto.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError extends ErrorOutcome {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    public ApiError(HttpStatus status, String message, Throwable ex) {
        timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        if (debugMessage == null){
            return "";
        } else {
            return debugMessage;
        }
    }

    public HttpStatus getStatus() {
        return status;
    }
}
