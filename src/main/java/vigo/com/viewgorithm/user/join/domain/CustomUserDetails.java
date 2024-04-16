package vigo.com.viewgorithm.user.join.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final User userEntity;
    // 생성자
    public CustomUserDetails(User userEntity) {
        this.userEntity = userEntity;
    }
    // getAuthorities = 사용자 권한 반환하는 인터페이스 함수

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // ArrayList 를 사용하여 권한을 저장하는 collection

        // 사용자 권한 설정
        switch (userEntity.getUserType()) {
            case "user":
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
            case "admin":
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
