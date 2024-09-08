package com.staybooker.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER(1), ADMIN(2);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public static Role valueOf(int id) {
        return switch (id) {
            case 1 -> Role.USER;
            case 2 -> Role.ADMIN;
            default -> throw new IllegalArgumentException("Invalid role");
        };
    }
}
