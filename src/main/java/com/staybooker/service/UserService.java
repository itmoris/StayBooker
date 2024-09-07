package com.staybooker.service;

import com.staybooker.dao.UserDao;
import com.staybooker.dto.UserAuthenticateDto;
import com.staybooker.dto.UserRegistrationDto;
import com.staybooker.entity.User;
import com.staybooker.exception.AuthenticateException;
import com.staybooker.exception.ValidationException;
import com.staybooker.validator.RegistrationValidator;
import com.staybooker.validator.Validator;
import lombok.Getter;

import java.util.Objects;

import static com.staybooker.mapper.UserMapper.toEntity;

public class UserService {
    private final UserDao userDao = UserDao.getINSTANCE();
    private final Validator<UserRegistrationDto> validator = new RegistrationValidator();
    @Getter
    private static final UserService INSTANCE = new UserService();

    private UserService() {
    }

    public void registration(UserRegistrationDto dto) throws ValidationException {
        validator.validate(dto);
        userDao.save(toEntity(dto));
    }

    public void authenticate(UserAuthenticateDto dto) throws AuthenticateException {
        User user = userDao.getByEmail(dto.getEmail());

        if (Objects.isNull(user)) {
            throw new AuthenticateException("Invalid email or password");
        } else if (!user.getPassword().equals(dto.getPassword())) {
            throw new AuthenticateException("Invalid password");
        }
    }
}
