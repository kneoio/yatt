package com.semantyca.yatt.dto.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@JsonPropertyOrder({"timestamp", "errorFields"})
public class ValidationError implements IErrorPage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private Map<String, FieldError> errorFields = new HashMap<>();

    public ValidationError(String message) {
        timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void addError(String fieldName, String error) {
        errorFields.put(fieldName, new FieldError(fieldName, error));

    }

    @JsonIgnore
    public String getMessage() {
        return errorFields.values().stream().map(v -> v.getHelperText()).collect(Collectors.joining("", "", "\\n\\n"));
    }


    public Map<String, FieldError> getErrorFields() {
        return errorFields;
    }
}
