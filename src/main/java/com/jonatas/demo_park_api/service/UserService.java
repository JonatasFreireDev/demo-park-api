package com.jonatas.demo_park_api.service;

import org.springframework.stereotype.Service;

import com.jonatas.demo_park_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

}
