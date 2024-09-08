package com.staybooker.validator;

import com.staybooker.dto.UserAuthenticateDto;
import com.staybooker.exception.ValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserAuthenticateValidator implements Validator<UserAuthenticateDto> {
    @Getter
    private static final UserAuthenticateValidator INSTANCE = new UserAuthenticateValidator();

    @Override
    public void validate(UserAuthenticateDto dto) throws ValidationException {
        String email = dto.getEmail();
        if (email == null || email.isEmpty()) {
            throw new ValidationException("Email cannot be empty");
        }

        String password = dto.getPassword();
        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password cannot be empty");
        }
    }
}
