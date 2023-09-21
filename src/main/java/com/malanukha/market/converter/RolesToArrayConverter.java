package com.malanukha.market.converter;

import com.malanukha.market.domain.user.Role;
import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RolesToArrayConverter implements AttributeConverter<Set<Role>, String[]> {

    @Override
    public String[] convertToDatabaseColumn(Set<Role> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return new String[]{};
        }
        return attribute.stream().map(Role::name).toArray(String[]::new);
    }

    @Override
    public Set<Role> convertToEntityAttribute(String[] dbData) {
        if (dbData == null || dbData.length == 0) {
            return EnumSet.noneOf(Role.class);
        }
        return Arrays.stream(dbData).map(Role::valueOf).collect(Collectors.toSet());
    }
}