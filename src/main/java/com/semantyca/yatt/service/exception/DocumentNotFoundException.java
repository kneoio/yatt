package com.semantyca.yatt.service.exception;

import java.util.UUID;

public class DocumentNotFoundException extends Exception {
    public DocumentNotFoundException(UUID id) {
        super();
    }
}
