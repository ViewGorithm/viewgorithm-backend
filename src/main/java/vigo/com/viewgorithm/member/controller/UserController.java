package vigo.com.viewgorithm.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vigo.com.viewgorithm.member.dto.MemberResponseDto;
import vigo.com.viewgorithm.member.dto.TokenDto;
import vigo.com.viewgorithm.member.dto.MemberRequestDto;
import vigo.com.viewgorithm.member.error.RefreshTokenInvalidException;
import vigo.com.viewgorithm.member.service.MemberService;


@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final MemberService memberService;

    @PostMapping("/user/join")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto userRequestDto) {
        return ResponseEntity.ok(memberService.signup(userRequestDto));
    } // 예외처리 o

    @PostMapping("/user/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto userRequestDto) {
        return ResponseEntity.ok(memberService.login(userRequestDto));
    } // 예외처리 o



    @PostMapping("/user/refresh")
    public ResponseEntity<TokenDto> refresh(HttpServletRequest request) {
        try {
            TokenDto tokenDto = memberService.refresh(request);
            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
            log.error("this token is invaild.");
            return ResponseEntity.internalServerError().build();
        }
    }
    // 예외처리 o

    @PostMapping("/admin/join")
    public ResponseEntity<MemberResponseDto> adminSignup(@RequestBody MemberRequestDto userRequestDto) {
        return ResponseEntity.ok(memberService.adminSignup(userRequestDto));
    }
    // 예외처리 o



    //test api
    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("Hello User");
    }

    //test api
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("Hello Admin");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello");
    }
}
