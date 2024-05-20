package vigo.com.viewgorithm.member.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class JwtBlacklistService {

    private static final String BLACKLIST_PREFIX = "blacklist:";
    private static final String REFRESH_BLACKLIST_PREFIX = "refresh_blacklist:";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void blacklistToken(String token, Duration duration) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(BLACKLIST_PREFIX + token, true, duration);
    }

    public boolean isTokenBlacklisted(String token) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        Boolean isBlacklisted = (Boolean) ops.get(BLACKLIST_PREFIX + token);
        return isBlacklisted != null && isBlacklisted;
    }

    public boolean isRefreshTokenBlacklisted(String refreshToken) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        Boolean isBlacklisted = (Boolean) ops.get(REFRESH_BLACKLIST_PREFIX + refreshToken);
        return isBlacklisted != null && isBlacklisted;
    }
}
