package com.service.api.note.converter;

import com.service.api.note.dto.UserSaveDto;
import com.service.api.note.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserSaveDtoMapper {

    public User convertFromDto(UserSaveDto dto) {
        return User.builder()
                .id(dto.getId() == null ? null : UUID.fromString(dto.getId()))
                .firstName(dto.getFirstName())
                .lastName(dto.getFirstName())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .role(dto.getRole())
                .status(dto.getStatus())
                .build();
    }
}
