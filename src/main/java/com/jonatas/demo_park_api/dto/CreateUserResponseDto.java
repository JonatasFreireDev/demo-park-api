package com.jonatas.demo_park_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUserResponseDto {
    private Long id;
    private String username;
    private String role;
}
