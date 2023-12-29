package com.example.online_movie_ticketing_application.WebSecurityConfig;

import com.example.online_movie_ticketing_application.Entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CurrentSessionUserDetails implements UserDetails {

    private final UserEntity userEntity;

    public CurrentSessionUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getName();
    }

    public int getId(){
        return userEntity.getId();
    }

    public String getAddress(){
        return userEntity.getAddress();
    }

    public int age(){
        return  userEntity.getAge();
    }

    public String mobNo(){
        return userEntity.getMobNo();
    }

    public String email(){
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
