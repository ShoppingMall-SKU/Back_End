package com.springboot.mealkart.oauth2;

import com.springboot.mealkart.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public class CustomOAuth2User implements OAuth2User {

    private final User user;

    public CustomOAuth2User(User user){
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of("userId", user.getUserId(), "name", user.getName(), "email", user.getEmail());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add((GrantedAuthority) () -> user.getUserRole().name());

        return collection;
    }

    @Override
    public String getName() {
        return user.getName();
    }

    public String getUserId() {
        return user.getUserId();
    }

    public String getSocialType() { return user.getSocialType().name();}

    public String getEmail() {return user.getEmail();}
}
