package com.staybooker.validator;

import com.staybooker.exception.ValidationException;

public interface Validator<T> {
    void validate(T type) throws ValidationException;
}
