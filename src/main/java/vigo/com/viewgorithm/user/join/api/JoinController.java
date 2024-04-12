package vigo.com.viewgorithm.user.join.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vigo.com.viewgorithm.user.join.api.dto.UserDto;
import vigo.com.viewgorithm.user.join.service.JoinService;

@RestController // ResponseBody + Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class JoinController {
    private final JoinService joinService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto){

        joinService.userJoin(userDto);
        return ResponseEntity.ok().body("User Join Success.");
    }
}

