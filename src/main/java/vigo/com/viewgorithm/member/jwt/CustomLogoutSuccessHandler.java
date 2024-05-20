package vigo.com.viewgorithm.member.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Duration;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private JwtBlacklistService jwtBlacklistService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        String refreshToken = resolveRefreshToken(request);
        if (StringUtils.hasText(refreshToken)) {
            // 리프레시 토큰만 블랙리스트에 추가
            // jwtBlacklistService.blacklistToken(refreshToken, Duration.ofMinutes(30)); // 30분 동안 블랙리스트 유지
            jwtBlacklistService.blacklistToken(refreshToken, null); // 영구히 블랙리스트에 추가
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
    }

    private String resolveRefreshToken(HttpServletRequest request) {
        // 프론트엔드에서 전송한 리프레시 토큰을 추출하는 방법에 따라 구현합니다.
        // 여기에서는 단순히 요청 헤더에서 추출하는 예시를 제공합니다.
        String refreshToken = request.getHeader("Refresh-Token");
        if (StringUtils.hasText(refreshToken)) {
            return refreshToken;
        }

        return null;
    }
}
