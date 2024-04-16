package vigo.com.viewgorithm.user.auth.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vigo.com.viewgorithm.user.auth.service.UserService;
import vigo.com.viewgorithm.user.auth.api.dto.JwtDto;
import vigo.com.viewgorithm.user.auth.api.dto.SiginInDto;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;

    // 로그인 기능
    @PostMapping("/login")
    public JwtDto signIn(@RequestBody SiginInDto signInDto) {

        String username = signInDto.getUserId();
        String password = signInDto.getPassword();
        JwtDto jwtDto = userService.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtDto accessToken = {}, refreshToken = {}", jwtDto.getAccessToken(), jwtDto.getRefreshToken());
        return jwtDto;

    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
