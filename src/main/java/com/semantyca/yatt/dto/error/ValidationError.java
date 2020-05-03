package com.semantyca.yatt.dto.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonPropertyOrder({"timestamp","message", "fields"})
public class ValidationError implements IErrorPage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private List<FieldError> fields = new ArrayList<>();


    public ValidationError(String message) {
        timestamp = LocalDateTime.now();

    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void addError(String fieldName, String error) {
        fields.add(new FieldError(fieldName, error));

    }

    public String getMessage() {
        return fields.stream().map(v -> v.getErrorMessage()).collect(Collectors.joining("", "", "\\n\\n"));
    }


    public List<FieldError> getFields() {
        return fields;
    }
}
