package vigo.com.viewgorithm.user.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vigo.com.viewgorithm.user.dto.UserDto;
import vigo.com.viewgorithm.user.service.UserProvider;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserProvider userProvider;
    @PostMapping("/user/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto){

        userProvider.join(userDto);

        return ResponseEntity.ok().body("회원가입이 성공 했습니다.");
    }

}
