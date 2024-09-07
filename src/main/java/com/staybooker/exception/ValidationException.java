package com.staybooker.exception;

import lombok.Getter;

@Getter
public class ValidationException extends Exception {
    private String field;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String field, String message) {
        super(message);
        this.field = field;
    }
}
