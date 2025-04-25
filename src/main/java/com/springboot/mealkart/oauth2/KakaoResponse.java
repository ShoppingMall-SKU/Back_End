package com.springboot.mealkart.oauth2;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoResponse implements OAuth2Response {

    private  final Map<String, Object> attribute;

    @Override
    public String getSocialType() {
        return "KAKAO";
    }

    @Override
    public String getUserId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail(){
        return attribute.get("KakaoAccount.email").toString();
    }

    @Override
    public String getName(){
        return attribute.get("KakaoAccount.name").toString();
    }

}
