package vigo.com.viewgorithm.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vigo.com.viewgorithm.user.domain.User;
import vigo.com.viewgorithm.user.dto.CustomUserDetails;
import vigo.com.viewgorithm.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    // 처음에 메소드를 오버라이딩 시키지 않아서 에러가 발생한다.

    private final UserRepository userRepository;


    // 데이터 베이스에서 특정 유저를 조회해서 리턴
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        // 여기 부분에서 데이터를 못찾는것같다.
        User userData = userRepository.findByUserId(userId);
        System.out.println(userData);
        // 검증
        if(userData != null){
            return new CustomUserDetails(userData);
        }

    return null;
    }

}
