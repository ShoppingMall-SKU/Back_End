package com.springboot.mealkart.dto;

import com.springboot.mealkart.domain.Seller;
import lombok.Getter;

@Getter
public record SellerUpdateDto(
        String password,
        String brandName
) {
    public Seller toEntity() {
        return Seller.builder()
                .password(password)
                .brandName(brandName)
                .build();
    }
}
