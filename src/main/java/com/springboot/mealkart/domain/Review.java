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
@Table(name = "TB_REVIEW")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseDomain {

    @Id
    @Column(name = "REVIEW_UUID")
    private String reviewUuid;

    @Column(name = "SCORE")
    private Integer score;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "IMAGE")
    private String image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_UUID")
    private Product productUuid;

    @OneToOne(fetch = FetchType.LAZY)
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
    public Review (Integer score,
                   String content,
                   String image,
                   Product productUuid,
                   User userUuid) {
        this.reviewUuid = UtilMethod.createUUID();
        this.score = score;
        this.content = content;
        this.image = image;
        this.productUuid = productUuid;
        this.userUuid = userUuid;
    }
}
