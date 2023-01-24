package com.service.api.note.converter;

import com.service.api.note.dto.UserProfileDto;
import com.service.api.note.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserProfileDtoMapper {

    public UserProfileDto convertToDto(User user) {
        return UserProfileDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getFirstName())
                .username(user.getUsername())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}
