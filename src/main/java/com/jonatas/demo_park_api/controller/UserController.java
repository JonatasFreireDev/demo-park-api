package com.jonatas.demo_park_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.demo_park_api.dto.CreateUserDto;
import com.jonatas.demo_park_api.dto.CreateUserResponseDto;
import com.jonatas.demo_park_api.dto.UserPasswordDto;
import com.jonatas.demo_park_api.dto.mapper.UserMapper;
import com.jonatas.demo_park_api.entity.User;
import com.jonatas.demo_park_api.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResponseDto> create(@RequestBody CreateUserDto user) {
        User newUser = userService.save(UserMapper.toUser(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponseDto(newUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateUserResponseDto> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toUserResponseDto(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id,
            @RequestBody UserPasswordDto dto) {
        userService.changePassword(id, dto.getPassword(), dto.getNewPassword(),
                dto.getConfirmNewPassword());
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
}
