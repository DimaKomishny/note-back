package com.service.api.note.facade.impl;

import com.service.api.note.converter.UserProfileDtoMapper;
import com.service.api.note.converter.UserSaveDtoMapper;
import com.service.api.note.dto.UserProfileDto;
import com.service.api.note.dto.UserSaveDto;
import com.service.api.note.facade.UserFacade;
import com.service.api.note.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserSaveDtoMapper userSaveDtomapper;
    private final UserProfileDtoMapper userProfileDtoConvertor;

    @Override
    public List<UserProfileDto> getAllUsers() {
        return userService.getAll()
                .stream()
                .map(userProfileDtoConvertor::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveUser(UserSaveDto userDto) {
        var user = userSaveDtomapper.convertFromDto(userDto);
        userService.saveUser(userSaveDtomapper.convertFromDto(userDto));
        log.info("user {} was saved", user);
    }

    @Override
    public UserProfileDto getUserProfile(String username) {
        var user = userService.findByUsername(username);
        return userProfileDtoConvertor.convertToDto(user);
    }

    @Override
    public void updateYourself(UserSaveDto userDto, String requesterUsername) {
        var user = userSaveDtomapper.convertFromDto(userDto);
        userService.saveYourself(user, requesterUsername);
    }
}
