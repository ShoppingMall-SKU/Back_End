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
@Table(name = "TB_PAYMENT")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseDomain {

    @Id
    @Column(name = "PAYMENT_UUID")
    private String paymentUuid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERING_UUID")
    private Ordering orderingUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_UUID")
    private User userUuid;

    @Column(name = "PAYMENT_AMT")
    private Integer paymentAmt;

    // TODO EMUN클래스로 변경 필요
    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    // 환불 여부
    @Column(name = "REFUND_YN", columnDefinition = "CHAR(1)")
    private String refundYn;

    @Column(name = "USE_YN", columnDefinition = "CHAR(1)")
    private String useYn;

    @PrePersist
    public void prePersist() {
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
        this.refundYn = StringUtils.hasLength(this.refundYn) ? "N" : this.refundYn;
    }

    @PreUpdate
    public void PreUpdate() {
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
    }

    @Builder
    public Payment (Ordering orderingUuid,
                    User userUuid,
                    Integer paymentAmt,
                    String paymentType) {
        this.paymentUuid = UtilMethod.createUUID();
        this.orderingUuid = orderingUuid;
        this.userUuid = userUuid;
        this.paymentType = paymentType;
        this.paymentAmt = paymentAmt;
    }
}
