package com.staybooker.mapper;

import com.staybooker.dto.UserAuthenticateDto;
import com.staybooker.dto.UserDto;
import com.staybooker.dto.UserRegistrationDto;
import com.staybooker.entity.User;
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
