package com.springboot.mealkart.service;

import com.springboot.mealkart.domain.Seller;
import com.springboot.mealkart.dto.SellerCreateDto;
import com.springboot.mealkart.dto.SellerLoginDto;
import com.springboot.mealkart.exception.CommonException;
import com.springboot.mealkart.exception.ErrorCode;
import com.springboot.mealkart.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class SellerService {

    private final SellerRepository sellerRepository;

    /**
     * Seller 생성 메소드
     * @param dto
     * @return
     */
    public Boolean createSeller(SellerCreateDto dto) {
        sellerRepository.save(dto.toEntity());
        return true;
    }

    /**
     * 팡매자 로그인 메소드
     * @param dto
     * @return
     */
    public Boolean sellerLogin(SellerLoginDto dto) {
        // 시큐리티 추가 후 수정 예정. 2025.04.06 added by hwanhee
        return true;
    }

    /**
     * 삭제(탈퇴) 메소드
     * @param sellerUUID
     * @return
     */
    public Boolean sellerDelete(String sellerUUID) {
        Seller seller = sellerRepository
                .findById(sellerUUID)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        return seller.deactivate();
    }

    /**
     * 비밀 번호 변경  메소드
     * @param sellerUUID
     * @param newPw
     * @return
     */
    public Boolean sellerPwUpdate(String sellerUUID, String newPw) {
        sellerRepository
                .findById(sellerUUID)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE))
                .updatePasswordSeller(newPw);
        return true;
    }

    /**
     * 브랜드이름 변경 메소드
     * @param sellerUUID
     * @param newBrandName
     * @return
     */
    public Boolean sellerBrandNameUpdate(String sellerUUID, String newBrandName) {
        sellerRepository
                .findById(sellerUUID)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE))
                .updateBrandNameSeller(newBrandName);
        return true;
    }

}
