package vigo.com.viewgorithm.user.join.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final User userEntity;

    public CustomUserDetails(User userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_USER");

//        System.out.println(authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 사용자 계정 만료 여부
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 사용자 계정 잠김 여부
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 사용자 자격 증명 만료 여부
    }

    @Override
    public boolean isEnabled() {
        return true; // 사용자 활성화 여부
    }
}
