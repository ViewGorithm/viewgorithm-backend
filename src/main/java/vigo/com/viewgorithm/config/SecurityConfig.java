package vigo.com.viewgorithm.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vigo.com.viewgorithm.user.jwt.JwtAccessDeniedHandler;
import vigo.com.viewgorithm.user.jwt.JwtAuthenticationEntryPoint;
import vigo.com.viewgorithm.user.jwt.JwtFilter;
import vigo.com.viewgorithm.user.jwt.TokenProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf disable => csrf protect disable

        http
                .csrf(AbstractHttpConfigurer::disable)
                // form 로그인 방식 disable
                .formLogin(AbstractHttpConfigurer::disable)
                // http basic 인증 방식 disable
                .httpBasic(AbstractHttpConfigurer::disable)
                // 경로별 인가 작업 - 모든 경로 경로 인가 해줌
                .authorizeHttpRequests((request) -> request.requestMatchers("/user/**").permitAll().anyRequest().hasAuthority("ROLE_USER"))
                // user 경로를 통한 경로는 전부다 허용, algorithm 부분은 로그인을 해야지 들어갈 수 있음

                // add at 은 원하는 자리에 등록
                // add before은 해당하는 필터 이전에 등록
                // add after는 이후에
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exception) -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler))
                // 필터를 등록한다. LoginFilter를 등록하는데 매개변수로 Manage가 들어가야한다. => bean 등록 필요
                // 세션 설정
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}