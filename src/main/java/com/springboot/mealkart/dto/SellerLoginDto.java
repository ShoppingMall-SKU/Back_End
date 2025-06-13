package com.springboot.mealkart.dto;

import lombok.Getter;

@Getter
public record SellerLoginDto(
        String sellerId,
        String password
) {
}
