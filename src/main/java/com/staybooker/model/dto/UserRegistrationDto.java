package com.staybooker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRegistrationDto {
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
}
