package com.springboot.mealkart.dto;

import com.springboot.mealkart.domain.User;

public record UserRegDto(
        String userId,
        String password,
        String name,
        String streetAddress,
        String detailAddress,
        String zipcode,
        String generalNum,
        String phoneNumber,
        String email
) {

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .streetAddress(streetAddress)
                .detailAddress(detailAddress)
                .zipcode(zipcode)
                .genaralNum(generalNum)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }
}
