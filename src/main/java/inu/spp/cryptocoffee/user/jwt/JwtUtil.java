package inu.spp.cryptocoffee.user.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Jwt Token을 발행하고, 검증하는 Util Class
 */
@Component
public class JwtUtil {

    private final SecretKey secretKey;

    /**
     * secret code에 관한 내용은 내부에 하드코딩 X -> application에 저장
     * 양방향 대칭키 방식인 HS256 사용
     */
    public JwtUtil(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    /**
     * 현재 서버의 secretKey를 통해서 만들어진 것을 확인하고
     * Jwt Token에 담긴 username 정보를 String Class 형태로 반환
     */
    public String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    /**
     * 현재 서버의 secretKey를 통해서 만들어진 것을 확인하고
     * Jwt Token에 담긴 role 정보를 String Class 형태로 반환
     */
    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    /**
     * 현재 서버의 secretKey를 통해서 만들어진 것을 확인하고
     * Jwt Token에 담긴 expire 정보와 현재 시간을 비교해 만료 확인
     */
    public void isExpired(String token) {
        Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration();
    }

    /**
     * 현재 서버의 secretKey를 통해서 만들어진 것을 확인하고
     * Jwt Token에 담긴 category 정보를 String Class 형태로 반환
     */
    public String getCategory(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

    public String createJwt(String category, String username, String role, Long expiredMs) {
        return Jwts.builder()
                .header().add("typ", "JWT").and()                        // header
                .claim("category", category)                            // payload
                .claim("username", username)                            // payload
                .claim("role", role)                                    // payload
                .issuedAt(new Date(System.currentTimeMillis()))               // 발행시간
                .expiration(new Date(System.currentTimeMillis() + expiredMs)) // 발행시간 + 활성시간 -> 만료시간
                .signWith(secretKey)                                          // secretKey를 통한 암호화
                .compact();
    }
}
