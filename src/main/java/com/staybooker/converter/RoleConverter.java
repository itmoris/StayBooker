package com.staybooker.converter;

import com.staybooker.entity.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Role attribute) {
        return attribute.getId();
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        return Role.valueOf(dbData);
    }
}
