package com.service.api.note.service;

import com.service.api.note.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> getAll();
    User findByUsername(String username);
    User findById(UUID id);
    void delete(UUID id);
    User saveUser(User user);
    void saveYourself(User user, String requesterUsername);
}
