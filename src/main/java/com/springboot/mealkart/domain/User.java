package com.springboot.mealkart.domain;


import com.springboot.mealkart.common.domain.BaseDomain;
import com.springboot.mealkart.common.util.JpaCryptoConverter;
import com.springboot.mealkart.enumerate.SocialType;
import com.springboot.mealkart.enumerate.UserRole;
import com.springboot.mealkart.common.util.UtilMethod;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "TB_USER")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseDomain {

    @Id
    @Column(name = "USER_UUID")
    private String userUuid;

    @Column(name = "USER_ROLE") // TODO 삭제 필요
    private UserRole userRole;

    @Column(name = "ZIPCODE")
    private String zipcode;

    @Column(name = "STREET_ADDRESS")
    private String streetAddress;

    @Column(name = "DETAIL_ADDRESS")
    private String detailAddress;

    @Column(name = "NAME")
    private String name;

    // 2025.04.24 added by hwanhee. 일반 전화 null이 가능하지만 있어야함.
    @Column(name = "GENARAL_NUM")
    private String genaralNum;

    @Column(name = "PHONE_NUMBER")
    //@Convert(converter = JpaCryptoConverter.class)
    private String phoneNumber;

    @Column(name = "SOCIAL_TYPE")
    private SocialType socialType;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "PASSWORD")
    //@Convert(converter = JpaCryptoConverter.class)
    private String password;

    @Setter
    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    //계정잠김여부
    @Column(name = "ACNT_LOCK_YN")
    private String acntLockYn;

    //비밀번호오류횟수
    @Column(name = "PSWD_ERR_NMTM")
    private Integer pswdErrNmtm;

    @Column(name = "USE_YN", columnDefinition = "CHAR(1)")
    private String useYn;

    @PrePersist
    public void prePersist() {
        this.acntLockYn = StringUtils.hasLength(this.acntLockYn) ? "N" : this.acntLockYn;
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
    }

    @PreUpdate
    public void PreUpdate() {
        this.acntLockYn = StringUtils.hasLength(this.acntLockYn) ? "N" : this.acntLockYn;
        this.useYn = StringUtils.hasLength(this.useYn) ? "Y" : this.useYn;
    }

    @Builder
    public User (String name,
                 String zipcode,
                 String streetAddress,
                 String detailAddress,
                 String genaralNum,
                 String phoneNumber,
                 String email,
                 String userId,
                 String password,
                 String refreshToken,
                 String acntLockYn,
                 Integer pswdErrNmtm,
                 SocialType socialType,
                 UserRole userRole) {
        this.userUuid = UtilMethod.createUUID();
        this.name = name;
        this.zipcode = zipcode;
        this.streetAddress = streetAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.refreshToken = refreshToken;
        this.acntLockYn = acntLockYn;
        this.pswdErrNmtm = pswdErrNmtm;
        this.socialType = socialType;
        this.userRole = userRole;
        this.userId = userId;
        this.genaralNum = genaralNum;

    }
}
