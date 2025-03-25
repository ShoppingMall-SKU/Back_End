package com.springboot.mealkart.domain;


import com.springboot.mealkart.common.domain.BaseDomain;
import com.springboot.mealkart.common.util.UtilMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.util.StringUtils;
//import javax.persistence.*;

@Entity
@Table(name = "TB_ORDERING_DETAIL")
@Getter
@IdClass(OrderingDetailPK.class)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderingDetail extends BaseDomain implements Persistable<OrderingDetailPK> {

    @Id
    @Column(name = "ORDERING_UUID")
    private String orderingUuid;

    @Id
    @Column(name = "ORDERING_DETAIL_UUID")
    private String orderingDetailUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_UUID", insertable = false, updatable = false)  // 외래키 매핑용
    private Ordering ordering;

    @Column(name = "PRODUCT_UUID")
    private String productUuid;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    // 배송 상태 값 (입금확인 / 배송 준비중 / 배송중 / 배송완료)
    @Column(name = "DELIVERY_CD")
    private String deliveryCd;

    // 주문 상태 값 (구매확정 / 환불 / 취소)
    @Column(name = "ORDERING_CD")
    private String orderingCd;

    @Column(name = "USE_YN", columnDefinition = "CHAR(1)")
    private String useYn;

    @PrePersist
    public void prePersist() {
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
        this.deliveryCd = StringUtils.hasLength(this.deliveryCd) ? "01" : this.deliveryCd;
    }

    @PreUpdate
    public void PreUpdate() {
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
    }

    @Builder
    public OrderingDetail (Ordering ordering,
                           String productUuid,
                           Integer price,
                           String orderingCd,
                           Integer quantity) {
        this.orderingDetailUuid = UtilMethod.createUUID();
        this.orderingUuid = ordering.getOrderUuid();
        this.ordering = ordering;
        this.productUuid = productUuid;
        this.price = price;
        this.orderingCd = orderingCd;
        this.quantity = quantity;
    }
    @Override
    public OrderingDetailPK getId(){
        return OrderingDetailPK.builder()
                .orderingUuid(this.orderingUuid)
                .orderingDetailUuid(this.orderingDetailUuid)
                .build();
    }
    @Override
    public boolean isNew() {
        return getCreateDate() == null;
    }
}
