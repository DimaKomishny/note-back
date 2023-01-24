package com.service.api.note.facade;

import com.service.api.note.dto.UserProfileDto;
import com.service.api.note.dto.UserSaveDto;

import java.util.List;

public interface UserFacade {

    List<UserProfileDto> getAllUsers();
    void saveUser(UserSaveDto userDto);
    UserProfileDto getUserProfile(String username);
    void updateYourself(UserSaveDto userDto, String requesterUsername);
}
