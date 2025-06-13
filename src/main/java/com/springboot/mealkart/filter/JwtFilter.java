package com.springboot.mealkart.filter;

import com.springboot.mealkart.domain.User;
import com.springboot.mealkart.enumerate.SocialType;
import com.springboot.mealkart.enumerate.UserRole;
import com.springboot.mealkart.exception.CommonException;
import com.springboot.mealkart.exception.ErrorCode;
import com.springboot.mealkart.jwt.JwtUtil;
import com.springboot.mealkart.oauth2.CustomOAuth2User;
import com.springboot.mealkart.repository.UserRepository;
import com.springboot.mealkart.service.RedisService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final RedisService redisService;
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info(request.getRequestURI());
        logger.info(request.getHeader("Authorization"));

/*        if (Constant.NO_FILTER_URLS.stream().anyMatch(request.getRequestURI()::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }*/

        logger.info("***************************");
        // 토큰
        String token = resolveToken(request);

        logger.info("----------------" + request.getHeader("Authorization"));

        try {
            //토큰에서 pid, role 획득
            String userId = jwtUtil.getUserId(token);
            String userRole = jwtUtil.getUserRole(token);
            String socialType = jwtUtil.getSocialType(token);

            if(socialType.isEmpty()) {
//            log.info("provider type is empty");
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                userId,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority(userRole)));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // 소셜 로그인 회원
            else {
                // user를 생성하여 값 set
                User user = User.builder()
                        .userId(userId)
                        .socialType(SocialType.valueOf(socialType))
                        .userRole(UserRole.USER_CUSTOMER) // 소셜 로그인을 사용하는건 구매자 밖에 없음.
                        .build();

                // UserDetails에 회원 정보 객체 담기
                CustomOAuth2User customOAuth2User = new CustomOAuth2User(user);

                // 스프링 시큐리티 인증 토큰 생성
                Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, "", customOAuth2User.getAuthorities());
                logger.info("******************************");
                // 세션에 사용자 등록
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            filterChain.doFilter(request,response);
        } catch (JwtException e) {

            String userId = jwtUtil.getUserId(token);

            User user = userRepository.findByUserId(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

            String providerType = user.getSocialType().name();
            String role = user.getUserRole().name();

            System.out.println("token expired.. regenerating...");
            try{
                redisService.findByKey(userId);
                Cookie cookie = new Cookie("Authorization",jwtUtil.createJwt(
                        userId,
                        providerType,
                        role,
                        60 * 60 *60L
                ));
                cookie.setMaxAge(60*60*60);

                cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setHttpOnly(false);
                response.addCookie(cookie);

                filterChain.doFilter(request,response);
                return;
            } catch(Exception ec2){
                throw new CommonException(ErrorCode.ACCESS_DENIED_ERROR);
            }
        }
//
//        // id, pw 로그인 회원
//        if(providerType.isEmpty()) {
////            log.info("provider type is empty");
//            Authentication authentication =
//                    new UsernamePasswordAuthenticationToken(
//                            pid,
//                            null,
//                            Collections.singletonList(new SimpleGrantedAuthority(role)));
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        // 소셜 로그인 회원
//        else {
//            // user를 생성하여 값 set
//            User user = User.builder()
//                    .pid(pid)
//                    .providerType(ProviderType.toEntity(providerType))
//                    .role(UserRole.toEntity(role))
//                    .build();
//
//            // UserDetails에 회원 정보 객체 담기
//            CustomOAuth2User customOAuth2User = new CustomOAuth2User(user);
//
//            // 스프링 시큐리티 인증 토큰 생성
//            Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, "", customOAuth2User.getAuthorities());
//
//            // 세션에 사용자 등록
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//        }

//        filterChain.doFilter(request,response);

    }

    private String resolveToken(HttpServletRequest request) {
        String BearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(BearerToken) && BearerToken.startsWith("Bearer")) {
            BearerToken  = BearerToken.replace("Bearer ", "");
            return BearerToken;
        }
        return null;
    }
}
