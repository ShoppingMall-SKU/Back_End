package com.springboot.mealkart.oauth2;

import com.springboot.mealkart.domain.User;
import com.springboot.mealkart.enumerate.SocialType;
import com.springboot.mealkart.enumerate.UserRole;
import com.springboot.mealkart.exception.CommonException;
import com.springboot.mealkart.exception.ErrorCode;
import com.springboot.mealkart.jwt.JwtUtil;
import com.springboot.mealkart.repository.UserRepository;
import com.springboot.mealkart.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final RedisService redisService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        try {
            log.info("******************1");

            OAuth2User oAuth2User = super.loadUser(userRequest);


            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            final OAuth2Response oAuth2Response;
            final SocialType socialType;

            if (registrationId.equals("google")){
                oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
                socialType = SocialType.TYPE_GOOGLE;
            }else if(registrationId.equals("kakao")) {
                oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
                socialType = SocialType.TYPE_KAKAO;
            }
            else {
                throw new CommonException(ErrorCode.AUTH_SERVER_USER_INFO_ERROR);
            }
            String userId = oAuth2Response.getUserId();

            return new CustomOAuth2User(
                    userRepository.findByEmail(oAuth2Response.getEmail())
                    .orElseGet(
                            () -> {
                                User user = User.builder()
                                        .userId(userId)
                                        .email(oAuth2Response.getEmail())
                                        .name(oAuth2Response.getName())
                                        .userRole(UserRole.USER_CUSTOMER)
                                        .socialType(socialType)
                                        .password("NULL")
                                        .build();
                                userRepository.save(user);
                                return user;
                            }
                    ));
        } catch (Exception e) {
            throw new CommonException(ErrorCode.AUTH_SERVER_USER_INFO_ERROR);
        }
    }
}
