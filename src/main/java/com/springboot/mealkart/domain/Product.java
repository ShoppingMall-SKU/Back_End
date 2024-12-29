package com.springboot.mealkart.domain;

import com.springboot.mealkart.enumerate.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PRODUCT")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @Column(name = "PRODUCT_UUID")
    private String productUuid;

    @Column(name = "NAME")
    private String name;

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

    @CreationTimestamp
    @Column(name = "CREATE_DT")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "LAST_DT")
    private LocalDateTime modifyDate;

    @Column(name = "USE_YN")
    private Character useYN;

    @Column(name = "SELLER_UUID")
    private String sellerUuid;

    @Builder
    public Product(String name,
                   String description,
                   String titleImg,
                   String detailImg,
                   String brand,
                   ProductStatus storeStatus,
                   Long price,
                   int saleRate,
                   LocalDateTime createDate,
                   LocalDateTime modifyDate) {
        //this.productUuid = CreateUuid()
        this.name = name;
        this. description = description;
        this.titleImg = titleImg;
        this. detailImg = detailImg;
        this.brand = brand;
        this.storeStatus = storeStatus;
        this.price = price;
        this.saleRate = saleRate;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.useYN = 'Y';
    }

    @PreUpdate
    public void preUpdate() {this.createDate = LocalDateTime.now();}


    }


