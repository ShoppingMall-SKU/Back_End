package com.springboot.mealkart.service;

import com.springboot.mealkart.dto.SellerLoginDto;
import com.springboot.mealkart.dto.UserLoginDto;
import com.springboot.mealkart.dto.UserRegDto;
import com.springboot.mealkart.exception.CommonException;
import com.springboot.mealkart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 일반 로그인 회원가입 메소드
     * @param dto
     * @return
     */
    public Boolean registerUser(UserRegDto dto) {
        if(userRepository.findByUserId(dto.userId()).isEmpty()) {
           userRepository.save(dto.toEntity());
           return true;
        }else {
            log.error("중복확인 제대로 처리되지 않음");
            return false;
        }
    }


}
