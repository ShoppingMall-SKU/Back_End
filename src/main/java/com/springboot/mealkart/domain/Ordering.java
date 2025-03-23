package com.springboot.mealkart.domain;

import com.springboot.mealkart.common.domain.BaseDomain;
import com.springboot.mealkart.common.util.UtilMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.math.BigInteger;

@Entity
@Table(name = "TB_ORDERING")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Ordering extends BaseDomain {

    // TODO 주문 번호 생성 메소드 필요
    @Id
    @Column(name = "ORDER_UUID")
    private String orderUuid;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "RECEIVE_NAME")
    private String receiveName;

    @Column(name = "RECEIVE_ADDRESS")
    private String receiveAdd;

    @Column(name = "RECEIVE_PHONE")
    private String receivePhone;

    @Column(name = "TOTAL_PRICE")
    private BigInteger totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_UUID")
    private User userUuid;

    @Column(name = "USE_YN", columnDefinition = "CHAR(1)")
    private String useYn;

    @PrePersist
    public void prePersist() {
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
    }

    @PreUpdate
    public void PreUpdate() {
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
    }

    @Builder
    public Ordering (String message,
                    String receiveName,
                    String receiveAdd,
                    String receivePhone,
                    BigInteger totalPrice,
                    User userUuid) {
        this.orderUuid = UtilMethod.createUUID();
        this.message = message;
        this.receiveName = receiveName;
        this.receiveAdd = receiveAdd;
        this.receivePhone = receivePhone;
        this.totalPrice = totalPrice;
        this.userUuid = userUuid;
    }
}
