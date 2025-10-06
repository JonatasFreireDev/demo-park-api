package com.jonatas.demo_park_api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonatas.demo_park_api.entity.User;
import com.jonatas.demo_park_api.exception.EntityNotFoundException;
import com.jonatas.demo_park_api.exception.PasswordInvalidException;
import com.jonatas.demo_park_api.exception.UsernameUniqueViolationException;
import com.jonatas.demo_park_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new UsernameUniqueViolationException(
                    String.format("Username '%s' is already taken", user.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with ID %d not found", id)));
    }

    @Transactional
    public User changePassword(Long id, String password, String newPassword, String confirmNewPassword) {
        if (!newPassword.equals(confirmNewPassword)) {
            throw new PasswordInvalidException("The new password and confirmation do not match");
        }

        User user = findById(id);
        if (!user.getPassword().equals(password)) {
            throw new PasswordInvalidException("Current password is incorrect");
        }

        user.setPassword(newPassword);
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
