package com.springboot.mealkart.dto;

import lombok.Getter;

@Getter
public record UserLoginDto(
        String id,
        String password
) {
}
