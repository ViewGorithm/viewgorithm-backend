package vigo.com.viewgorithm.user.join.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vigo.com.viewgorithm.user.join.domain.User;
import vigo.com.viewgorithm.user.join.api.dto.UserDto;
import vigo.com.viewgorithm.user.join.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class JoinService {
    private final UserRepository userRepository;

    @Transactional
    public String userJoin(UserDto userDto) {

        // username 중복 check -> repository 필요
        userRepository.findByUserId(userDto.getUserId())
                .ifPresent(user -> {
                    throw new RuntimeException(userDto.getUserId() + "is already exists");
                });
        User user = User.builder()
                .userId(userDto.getUserId())
                .password(userDto.getPassword())
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
}
