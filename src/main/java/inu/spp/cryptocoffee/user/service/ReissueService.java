package inu.spp.cryptocoffee.user.service;

import inu.spp.cryptocoffee.user.entity.RefreshEntity;
import inu.spp.cryptocoffee.user.jwt.JwtTimeComponent;
import inu.spp.cryptocoffee.user.jwt.JwtUtil;
import inu.spp.cryptocoffee.user.repository.RefreshTokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReissueService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final JwtTimeComponent jwtTimeComponent;

    public ResponseEntity<?> reissueRefreshToken(HttpServletRequest request, HttpServletResponse response) {

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return new ResponseEntity<>("cookie is null", HttpStatus.BAD_REQUEST);
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        // 토큰 검증
        ResponseEntity<String> BAD_REQUEST = verifiedRefreshToken(refresh);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        //make new JWT
        String newAccess = jwtUtil.createJwt("access", username, role, jwtTimeComponent.getAccessExpiration() * 1000);
        String newRefresh = jwtUtil.createJwt("refresh", username, role, jwtTimeComponent.getRefreshExpiration() * 1000);

        // 기존에 존재하는 Refresh 토큰 삭제
        refreshTokenRepository.findByToken(refresh).ifPresent(refreshTokenRepository::delete);
        // Refresh 토큰 저장
        RefreshEntity refreshEntity = RefreshEntity.builder()
                .username(username)
                .token(newRefresh)
                .expiration(jwtTimeComponent.getRefreshExpiration())
                .build();
        refreshTokenRepository.save(refreshEntity);

        //response
        response.addHeader("Authorization", "Bearer " + newAccess);
        response.addCookie(createCookie("refresh", newRefresh, jwtTimeComponent.getRefreshExpiration()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<String> verifiedRefreshToken(String refresh) {
        if (refresh == null) {
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refresh인지 확인
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        //DB에 저장되어 있는지 확인
        if (!refreshTokenRepository.existsByToken(refresh)) {
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private Cookie createCookie(String key, String value, Long refreshExpireTime) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Math.toIntExact(refreshExpireTime / 1000));
        //cookie.setPath("/");
        return cookie;
    }
}
