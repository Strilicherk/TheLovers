package com.strilicherk.theloversapi.core.domain.model.role;

import lombok.Getter;

@Getter
public enum RoleType {
    ADMIN(0, "Administrator"),
    PREMIUM(1, "Premium User"),
    COMMON(2, "Common User");

    private final int value;
    private final String description;

    RoleType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static RoleType fromValue(int value) {
        for (RoleType role : RoleType.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role value: " + value);
    }

    public static Role toEntity(RoleType type) {
        return new Role(type.getValue(), type, type.getDescription());
    }
}


