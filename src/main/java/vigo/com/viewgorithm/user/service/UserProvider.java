package vigo.com.viewgorithm.user.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vigo.com.viewgorithm.algorithm.dto.AlgorithmCodesDto;
import vigo.com.viewgorithm.algorithm.service.JwtTokenProvider;
import vigo.com.viewgorithm.user.domain.User;
import vigo.com.viewgorithm.user.dto.JwtToken;
import vigo.com.viewgorithm.user.dto.UserDto;
import vigo.com.viewgorithm.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j

public class UserProvider {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public String userJoin(UserDto userDto){

        // username 중복 check -> repository 필요
        userRepository.findByName(userDto.getUserId())
                .ifPresent(user -> {
                    throw new RuntimeException(userDto.getUserId() + "is already exists");
                });
        User user = User.builder()
                .userId(userDto.getUserId())
                .password(userDto.getPassWord())
                .name(userDto.getName())
                .birth(userDto.getBirth())
                .sex(userDto.getSex())
                .email(userDto.getEmail())
                .createdAt(userDto.getCreatedAt())
                .build();
        // save
        userRepository.save(user);
        return "SUCCESS";
    }
    @Transactional
    public JwtToken signIn(String username, String password) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;
    }
}


