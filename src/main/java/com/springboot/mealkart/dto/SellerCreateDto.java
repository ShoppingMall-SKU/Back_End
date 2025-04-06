package com.springboot.mealkart.dto;

import com.springboot.mealkart.domain.Seller;
import lombok.Getter;

@Getter
public record SellerCreateDto (
        String sellerNumber,
        String brandName,
        Integer deliveryFee,
        String sellerName,
        String telNumber,
        String email,
        String password,
        String sellerId
) {
    public Seller toEntity () {
        return Seller.builder()
                .deliveryFee(this.deliveryFee)
                .brandName(this.brandName)
                .sellerNumber(this.sellerNumber)
                .sellerName(this.sellerName)
                .sellerId(this.sellerId)
                .password(this.password)
                .telNumber(this.telNumber)
                .email(this.email)
                .build();
    }
}
