package com.staybooker.service;

import com.staybooker.dao.UserDao;
import com.staybooker.model.dto.UserAuthenticateDto;
import com.staybooker.model.dto.UserRegistrationDto;
import com.staybooker.model.entity.User;
import com.staybooker.exception.AuthenticateException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static com.staybooker.model.mapper.UserMapper.toEntity;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {
    private final UserDao userDao = UserDao.getINSTANCE();

    @Getter
    private static final UserService INSTANCE = new UserService();

    public void registration(UserRegistrationDto dto) {
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
