package com.example.bookstore.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class RestExceptionBase extends RuntimeException {
    private final String messageId;
    private final String debugId;

    public RestExceptionBase(String message, String messageId, String debugId) {
        super(message);
        this.messageId = messageId;
        this.debugId = debugId;
    }
}
