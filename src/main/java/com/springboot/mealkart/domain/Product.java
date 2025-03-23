package com.springboot.mealkart.domain;

import com.springboot.mealkart.common.domain.BaseDomain;
import com.springboot.mealkart.dto.ProductUpdateDto;
import com.springboot.mealkart.enumerate.ProductStatus;
import com.springboot.mealkart.common.util.UtilMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.math.BigInteger;

@Entity
@Table(name = "TB_PRODUCT")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseDomain {

    @Id
    @Column(name = "PRODUCT_UUID")
    private String productUuid;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TITLE_IMG")
    private String titleImg;

    @Column(name = "DETAIL_IMG")
    private String detailImg;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "STORE_STATUS")
    private ProductStatus storeStatus;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "SALE_RATE")
    private Integer saleRate;

    @Column(name = "STOCK")
    private BigInteger stock;

    @Column(name = "USE_YN", columnDefinition = "CHAR(1)")
    private String useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_UUID")
    private Seller sellerUuid;

    @PrePersist
    public void prePersist() {
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
    }

    @PreUpdate
    public void PreUpdate() {
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
    }

    @Builder
    public Product(String productName,
                   String description,
                   String titleImg,
                   String detailImg,
                   String brand,
                   ProductStatus storeStatus,
                   Long price,
                   Integer saleRate,
                   Seller sellerUuid,
                   BigInteger stock
                   ) {
        this.productUuid = UtilMethod.createUUID();
        this.productName = productName;
        this. description = description;
        this.titleImg = titleImg;
        this.detailImg = detailImg;
        this.brand = brand;
        this.storeStatus = storeStatus;
        this.price = price;
        this.saleRate = saleRate;
        this.sellerUuid = sellerUuid;
        this.stock = stock;
    }

    public void deactivate() {
        this.useYn = "N";
    }
    public void updateProduct(ProductUpdateDto productUpdateDto) {
        if (productUpdateDto.getProductName() != null) {
            this.productName = productUpdateDto.getProductName();
        }
        if (productUpdateDto.getDescription() != null) {
            this.description = productUpdateDto.getDescription();
        }
        if (productUpdateDto.getTitleImg() != null) {
            this.titleImg = productUpdateDto.getTitleImg();
        }
        if (productUpdateDto.getDetailImg() != null) {
            this.detailImg = productUpdateDto.getDetailImg();
        }
        if (productUpdateDto.getBrand() != null) {
            this.brand = productUpdateDto.getBrand();
        }
        if (productUpdateDto.getPrice() != null) {
            this.price = productUpdateDto.getPrice();
        }
        if (productUpdateDto.getSaleRate() != null) {
            this.saleRate = productUpdateDto.getSaleRate();
        }
        if (productUpdateDto.getStock() != null) {
            this.stock = productUpdateDto.getStock();
        }
    }
}


