package vigo.com.viewgorithm.member.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vigo.com.viewgorithm.member.jwt.JwtBlacklistService;

import java.util.Map;

    @RestController
    public class BlackListController {
        @Autowired
        private JwtBlacklistService jwtBlacklistService;

        // 블랙리스트에 Refresh 토큰이 들어갔는지 안들어갔는지 Check (안되는 상황)
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
