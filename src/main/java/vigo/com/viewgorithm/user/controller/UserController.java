package vigo.com.viewgorithm.user.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vigo.com.viewgorithm.user.dto.JwtToken;
import vigo.com.viewgorithm.user.dto.SiginInDto;
import vigo.com.viewgorithm.user.dto.UserDto;
import vigo.com.viewgorithm.user.service.UserProvider;
import lombok.extern.slf4j.Slf4j;

@RestController // ResponseBody + Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    
    private final UserProvider userProvider;

    // 회원가입 기능
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto){

        userProvider.userJoin(userDto);

        return ResponseEntity.ok().body("회원가입이 성공 했습니다.");
    }

    // 로그인 기능
    @PostMapping("/login")
    public JwtToken signin(@RequestBody SiginInDto signInDto) {
        String username = signInDto.getUserid();
        String password = signInDto.getPassword();
        JwtToken jwtToken = userProvider.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
