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
@Table(name = "TB_CART")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseDomain {

    @Id
    @Column(name = "CART_UUID")
    private String cartUuid;

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
    public Cart (User userUuid) {
        this.cartUuid = UtilMethod.createUUID();
        this.userUuid = userUuid;
    }
}
