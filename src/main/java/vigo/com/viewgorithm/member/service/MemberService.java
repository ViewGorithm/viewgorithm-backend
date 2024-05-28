package vigo.com.viewgorithm.member.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vigo.com.viewgorithm.member.domain.Member;
import vigo.com.viewgorithm.member.dto.MemberRequestDto;
import vigo.com.viewgorithm.member.dto.MemberResponseDto;
import vigo.com.viewgorithm.member.dto.TokenDto;
import vigo.com.viewgorithm.member.error.RefreshTokenInvalidException;
import vigo.com.viewgorithm.member.jwt.JwtBlacklistService;
import vigo.com.viewgorithm.member.jwt.TokenProvider;
import vigo.com.viewgorithm.member.repository.MemberRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final JwtBlacklistService jwtBlacklistService;

    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByUserId(memberRequestDto.getUserId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = memberRequestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    public MemberResponseDto adminSignup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByUserId(memberRequestDto.getUserId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = memberRequestDto.toAdmin(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        return tokenProvider.generateTokenDto(authentication);
    }



    @Transactional
    public TokenDto refresh(HttpServletRequest request) throws RefreshTokenInvalidException {
        String refreshToken = request.getHeader("Authorization").substring(7);
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }
        String userId = tokenProvider.getUserIdFromRefreshToken(refreshToken);
        Member member = memberRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));


        // 만약 refresh 토큰이 블랙리스트에 있다면
        if(jwtBlacklistService.isTokenBlacklisted(refreshToken)){
            throw new RefreshTokenInvalidException("This Token is invalid. User has Logged out.");
        } // 예외 발생

        return tokenProvider.regenerateToken(member);
        // 없다면 다시 토큰을 발급해준다.
    }

    @Transactional(readOnly = true)
    public boolean findByMemberId(Long MemberPk) {
        Optional<Member> member = memberRepository.findById(MemberPk);
        return member.isPresent();

    }


}
