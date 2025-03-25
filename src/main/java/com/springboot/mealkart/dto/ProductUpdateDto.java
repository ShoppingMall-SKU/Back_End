package com.springboot.mealkart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDto {

    private String productName;
    private String description;
    private String titleImg;
    private String detailImg;
    private String brand;
    private Long price;
    private Integer saleRate;
    private BigInteger stock;
}
