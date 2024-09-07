package com.staybooker.validator;

import com.staybooker.dto.UserRegistrationDto;
import com.staybooker.exception.ValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationValidator implements Validator<UserRegistrationDto> {
    @Getter
    private static final RegistrationValidator INSTANCE = new RegistrationValidator();

    @Override
    public void validate(UserRegistrationDto dto) throws ValidationException {
        String fullName = dto.getFullName();

        if (fullName == null || fullName.isBlank()) {
            throw new ValidationException("Full name cannot be blank");
        } else if (fullName.length() > 100) {
            throw new ValidationException("Full name cannot be longer than 100 characters");
        }

        String email = dto.getEmail();

        if (email == null || email.isBlank()) {
            throw new ValidationException("Email cannot be blank");
        } else if (email.length() > 100) {
            throw new ValidationException("Email must be at most 100 characters");
        }

        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();

        if (password == null || password.isBlank()) {
            throw new ValidationException("Password cannot be blank");
        } else if (confirmPassword == null || confirmPassword.isBlank()) {
            throw new ValidationException("Confirm password cannot be blank");
        } else if (!password.equals(confirmPassword)) {
            throw new ValidationException("Passwords do not match");
        } else if (password.length() > 500) {
            throw new ValidationException("Password cannot be longer than 500 characters");
        }

    }
}
