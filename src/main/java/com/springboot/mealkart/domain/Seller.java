package com.springboot.mealkart.domain;

import com.springboot.mealkart.common.domain.BaseDomain;
import com.springboot.mealkart.common.util.UtilMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "TB_SELLER")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller extends BaseDomain {

    @Id
    @Column(name = "SELLER_UUID")
    private String sellerUuid;

    // 사업자 번호
    @Column(name = "SELLER_NUMBER")
    private String sellerNumber;

    // 브랜드
    @Column(name = "BRAND_NAME")
    private String brandName;

    // 배송비
    @Column(name = "DELIVERY_FEE")
    private Integer deliveryFee;

    @Column(name = "SELLER_NAME")
    private String sellerName;

    // 전화번호
    @Column(name = "TEL_NUMBER")
    private String telNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "SELLER_ID")
    private String sellerId;

    @Column(name = "USE_YN", columnDefinition = "CHAR(1)")
    private String useYn;

    @PrePersist
    public void prePersist() {
        this.useYn = StringUtils.isEmpty(this.useYn) ? "Y" : this.useYn;
    }

    @PreUpdate
    public void PreUpdate() {
        this.useYn = StringUtils.isEmpty(this.useYn) ? "Y" : this.useYn;
    }

    @Builder
    public Seller (Integer deliveryFee,
                   String brandName,
                   String sellerNumber,
                   String sellerName,
                   String sellerId,
                   String password,
                   String telNumber,
                   String email){
        this.sellerUuid = UtilMethod.createUUID();
        this.sellerNumber = sellerNumber;
        this.brandName = brandName;
        this.deliveryFee = deliveryFee;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.password = password;
        this.telNumber = telNumber;
        this.email = email;
    }
}
