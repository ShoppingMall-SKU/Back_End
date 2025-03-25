package com.springboot.mealkart.dto;

import com.springboot.mealkart.domain.Product;
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
public class ProductDetailDto {

    private String productUuid;
    private String productName;
    private String description;
    private String titleImg;
    private String detailImg;
    private String brand;
    private ProductStatus storeStatus;
    private Long price;
    private Integer saleRate;
    private BigInteger stock;

    public ProductDetailDto(Product product) {
        this.productUuid = product.getProductUuid();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.titleImg = product.getTitleImg();
        this.detailImg = product.getDetailImg();
        this.brand = product.getBrand();
        this.storeStatus = product.getStoreStatus();
        this.price = product.getPrice();
        this.saleRate = product.getSaleRate();
        this.stock = product.getStock();
    }
}
