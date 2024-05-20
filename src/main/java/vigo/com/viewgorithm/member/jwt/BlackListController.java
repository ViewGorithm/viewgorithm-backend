package vigo.com.viewgorithm.member.jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

    @RestController
    public class BlackListController {
        @Autowired
        private JwtBlacklistService jwtBlacklistService;

        @PostMapping("/check")
        public ResponseEntity<?> checkRefreshToken(@RequestBody Map<String, String> requestBody) {
            String refreshToken = requestBody.get("refreshToken");

            if (refreshToken != null && !refreshToken.isEmpty()) { // refresh 토큰이 이
                boolean isBlacklisted = jwtBlacklistService.isRefreshTokenBlacklisted(refreshToken);

                if (isBlacklisted) {
                    return ResponseEntity.ok("Refresh Token is blacklisted");
                } else {
                    return ResponseEntity.ok("Refresh Token is valid");
                }
            } else {
                return ResponseEntity.badRequest().body("Refresh Token not provided");
            }
        }
    }
