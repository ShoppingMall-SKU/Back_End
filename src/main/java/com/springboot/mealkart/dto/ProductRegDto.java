package com.springboot.mealkart.dto;

import com.springboot.mealkart.domain.Seller;
import com.springboot.mealkart.enumerate.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegDto {

    private String productName;      // 상품명
    private String description;      // 상품 설명
    private String titleImg;         // 타이틀 이미지
    private String detailImg;        // 상세 이미지
    private String brand;            // 브랜드
    private ProductStatus storeStatus;  // 판매 상태 (Enum)
    private Long price;              // 가격
    private Integer saleRate;        // 할인율
    private BigInteger stock;        // 재고
    private Seller sellerUuid;       // 판매자 UUID
}
