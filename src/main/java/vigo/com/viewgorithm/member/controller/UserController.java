package vigo.com.viewgorithm.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vigo.com.viewgorithm.member.dto.MemberResponseDto;
import vigo.com.viewgorithm.member.dto.TokenDto;
import vigo.com.viewgorithm.member.dto.MemberRequestDto;
import vigo.com.viewgorithm.member.service.MemberService;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final MemberService memberService;

    @PostMapping("/user/join")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto userRequestDto) {
        return ResponseEntity.ok(memberService.signup(userRequestDto));
    }

    @PostMapping("/user/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto userRequestDto) {
        return ResponseEntity.ok(memberService.login(userRequestDto));
    }

    @PostMapping("/user/refresh")
    public ResponseEntity<TokenDto> refresh(HttpServletRequest request) {
        try {
            TokenDto tokenDto = memberService.refresh(request);
            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
            TokenDto errorResponse = new TokenDto(); // 에러 응답으로 빈 TokenDto 객체를 반환
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    @PostMapping("/admin/join")
    public ResponseEntity<MemberResponseDto> adminSignup(@RequestBody MemberRequestDto userRequestDto) {
        return ResponseEntity.ok(memberService.adminSignup(userRequestDto));
    }



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
