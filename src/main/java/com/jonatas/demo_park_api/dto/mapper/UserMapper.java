package com.jonatas.demo_park_api.dto.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.jonatas.demo_park_api.dto.CreateUserDto;
import com.jonatas.demo_park_api.dto.CreateUserResponseDto;
import com.jonatas.demo_park_api.entity.User;

public class UserMapper {

    public static User toUser(CreateUserDto createUserDto) {
        return new ModelMapper().map(createUserDto, User.class);
    }

    public static CreateUserResponseDto toUserResponseDto(User user) {
        String role = user.getRole().name().substring("ROLE_".length());
        PropertyMap<User, CreateUserResponseDto> prop = new PropertyMap<User, CreateUserResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(prop);
        return modelMapper.map(user, CreateUserResponseDto.class);
    }

    public static List<CreateUserResponseDto> toListDto(List<User> users) {
        return users.stream().map(UserMapper::toUserResponseDto).toList();
    }

}
