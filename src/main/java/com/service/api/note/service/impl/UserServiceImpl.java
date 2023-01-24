package com.service.api.note.service.impl;

import com.service.api.note.entity.User;
import com.service.api.note.repository.UserRepository;
import com.service.api.note.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User %s is not exist ", username)));
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with id %s is not exist ", id)));
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void saveYourself(User user, String requesterUsername) {
        var persistUser = userRepository.findById(user.getId()).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with id %s is not exist ", user.getId())));
        if (!persistUser.getUsername().equals(requesterUsername)) {
            throw new AccessDeniedException("User was tried updated another user");
        }
        userRepository.save(user);
    }
}
