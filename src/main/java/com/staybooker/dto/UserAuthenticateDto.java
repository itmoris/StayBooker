package com.staybooker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserAuthenticateDto {
    private String email;
    private String password;
}
