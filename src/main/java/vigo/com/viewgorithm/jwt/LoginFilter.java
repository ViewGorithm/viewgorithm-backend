package vigo.com.viewgorithm.jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 여기부분이 문제.
        String userid = obtainUsername(request);

        String password = obtainPassword(request);


        System.out.println(userid);
        // userid는 출력X
        System.out.println(password);
        //password는 잘 출력
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userid, password,null);

        return authenticationManager.authenticate(authToken);
    }

    // manage 검증 성공시 실행되는 메소드 (JWT 토큰 발급)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        System.out.println("Login Success");
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        System.out.println("Login failed");
    }



}
