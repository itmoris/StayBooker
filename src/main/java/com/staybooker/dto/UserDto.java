package com.staybooker.dto;

import com.staybooker.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private Role role;
}
