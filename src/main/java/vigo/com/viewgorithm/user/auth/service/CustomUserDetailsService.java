package vigo.com.viewgorithm.user.auth.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import vigo.com.viewgorithm.user.join.domain.User;
import vigo.com.viewgorithm.user.join.domain.repository.UserRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // 데이터 베이스에서 특정 유저를 조회해서 리턴
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 return
    private UserDetails createUserDetails(User user) {
        return User.builder()
                .userId(user.getUserId())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(List.of(user.getRoles().toArray(new String[0])))
                .build();
    }
}
