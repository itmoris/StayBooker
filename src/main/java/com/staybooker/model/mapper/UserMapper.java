package com.staybooker.model.mapper;

import com.staybooker.model.dto.UserAuthenticateDto;
import com.staybooker.model.dto.UserDto;
import com.staybooker.model.dto.UserRegistrationDto;
import com.staybooker.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public static UserRegistrationDto toUserRegistrationDto(HttpServletRequest request) {
        return UserRegistrationDto.builder()
                .fullName(request.getParameter("fullName"))
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .confirmPassword(request.getParameter("confirmPassword"))
                .build();
    }

    public static UserAuthenticateDto toUserAuthenticateDto(HttpServletRequest request) {
        return UserAuthenticateDto.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();
    }

    public static User toEntity(UserRegistrationDto dto) {
        return User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
