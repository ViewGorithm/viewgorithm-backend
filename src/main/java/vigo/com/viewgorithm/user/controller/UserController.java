package vigo.com.viewgorithm.user.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vigo.com.viewgorithm.user.dto.UserDto;
import vigo.com.viewgorithm.user.service.UserProvider;

@RestController // ResponseBody + Controller
@RequiredArgsConstructor
public class UserController {

    private final UserProvider userProvider;
    @PostMapping("/user/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto){

        userProvider.userJoin(userDto);

        return ResponseEntity.ok().body("회원가입이 성공 했습니다.");
    }

    // 로그인 기능
    @GetMapping("/")
    public String mainP(){
        return "Main Controller";
    }

}
