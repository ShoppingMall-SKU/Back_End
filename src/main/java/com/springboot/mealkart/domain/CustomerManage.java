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
@Table(name = "TB_CUSTOMER_MANAGE")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerManage extends BaseDomain {

    @Id
    @Column(name = "CUSTOMER_MANAGE_UUID")
    private String customerManageUuid;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "ATTACHMENT")
    private String attachment;

    // 셀러가 답변하는곳
    @Column(name = "ANWSER_CONTENT")
    private String answerContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_UUID")
    private Product productUuid;

    // 접수(0), 처리중(1), 완료(2) => 상태 코드값
    // 작성자 (구매자)
    // 수정자 (판매자) 상태코드  0일 경우 구매자도 수정 가능
    @Column(name = "MANAGE_CD")
    private String manageCd;

    @Column(name = "RELEASE_YN", columnDefinition = "CHAR(1)")
    private String releaseYn;

    @Column(name = "USE_YN", columnDefinition = "CHAR(1)")
    private String useYn;

    @PrePersist
    public void prePersist() {
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
        this.manageCd = StringUtils.hasLength(this.manageCd) ? "0" : this.manageCd;
    }

    @PreUpdate
    public void PreUpdate() {
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
    }

    @Builder
    public CustomerManage (String title,
                           String content,
                           String attachment,
                           String releaseYn,
                           String answerContent,
                           Product productUuid) {
        this.customerManageUuid = UtilMethod.createUUID();
        this.title = title;
        this.content = content;
        this.attachment = attachment;
        this.releaseYn = releaseYn;
        this.answerContent = answerContent;
        this.productUuid= productUuid;

    }
}
