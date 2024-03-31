package vigo.com.viewgorithm.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vigo.com.viewgorithm.algorithm.dto.AlgorithmCodesDto;
import vigo.com.viewgorithm.user.domain.User;
import vigo.com.viewgorithm.user.dto.UserDto;
import vigo.com.viewgorithm.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserProvider {

    private final UserRepository userRepository;

    public String join(UserDto userDto){

        // username 중복 check -> repository 필요
        userRepository.findByName(userDto.getUserId())
                .ifPresent(user -> {
                    throw new RuntimeException(userDto.getUserId() + "은 이미 있습니다.");

                });

        User user = User.builder()
                .userId(userDto.getUserId())
                .passWord(userDto.getPassWord())
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
