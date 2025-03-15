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
@Table(name = "TB_REFUND")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Refund extends BaseDomain {

    @Id
    @Column(name = "REFUND_UUID")
    private String refundUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ORDERING_UUID", referencedColumnName = "ORDERING_UUID"),
            @JoinColumn(name = "ORDERING_DETAIL_UUID", referencedColumnName = "ORDERING_DETAIL_UUID")
    })
    private OrderingDetail orderDetailUuid;

    // 취소환불 사유 (단순변심, 배송지연, 상품결함, 기타)
    @Column(name = "REASON_CD")
    private String reasonCd;

    @Column(name = "REASON_DETAIL")
    private String reasonDetail;

    // 판매자 답변 입력
    @Column(name = "ANWSER_DETAIL")
    private String answerDetail;

    // 취소(001) , 반품(002)
    @Column(name = "REFUND_CD")
    private String refundCd;

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
    public Refund (String reasonCd,
                   String reasonDetail,
                   String answerDetail,
                   String refundCd,
                   OrderingDetail orderDetailUuid) {
        this.refundUuid = UtilMethod.createUUID();
        this.reasonCd = reasonCd;
        this.reasonDetail = reasonDetail;
        this.answerDetail = answerDetail;
        this.refundCd = refundCd;
        this.orderDetailUuid = orderDetailUuid;
    }

}
