package com.springboot.mealkart.oauth2;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GoogleResponse implements OAuth2Response{

    private  final Map<String, Object> attribute;

    @Override
    public String getSocialType() {
        return "GOOGLE";
    }

    @Override
    public String getUserId() {
        return attribute.get("sub").toString();
    }

    @Override
    public String getEmail(){
        return attribute.get("email").toString();
    }

    @Override
    public String getName(){
        return attribute.get("name").toString();
    }
}
