package inu.spp.cryptocoffee.auth.user.jwt;

import inu.spp.cryptocoffee.auth.user.entity.RefreshEntity;
import inu.spp.cryptocoffee.auth.user.repository.RefreshTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;

@Slf4j
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final JwtTimeComponent jwtTimeComponent;

    public CustomLoginFilter(AuthenticationManager authenticationManager, RefreshTokenRepository refreshTokenRepository, JwtUtil jwtUtil, JwtTimeComponent jwtTimeComponent) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.jwtTimeComponent = jwtTimeComponent;
        setFilterProcessesUrl("/api/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //클라이언트 요청에서 username, password 추출
        final String username = obtainUsername(request);
        final String password = obtainPassword(request);

        log.info("[attemptAuthentication] username = {}", username);
        log.info("[attemptAuthentication] password = {}", password);

        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        log.info("[successfulAuthentication] 로그인 성공");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        final String username = authentication.getName();
        final String role = auth.getAuthority();
        final String access = jwtUtil.createJwt("access", username, role, jwtTimeComponent.getAccessExpiration() * 1000);
        final String refresh = jwtUtil.createJwt("refresh", username, role, jwtTimeComponent.getRefreshExpiration() * 1000);

        log.info("[successfulAuthentication] username = {}", username);
        log.info("[successfulAuthentication] access token = {}", access);
        log.info("[successfulAuthentication] refresh token = {}", refresh);
        //Refresh 토큰 저장
        addRefreshEntity(username, refresh, jwtTimeComponent.getRefreshExpiration());

        /**
         * HTTP 인증 방식은 RFC 7235 정의에 따라 아래 인증 헤더 형태를 가져야 한다.
         * Authorization: 타입 인증토큰
         * e.g. Authorization: Bearer 인증토큰str
         */
        response.addHeader("Authorization", "Bearer " + access);
        response.addCookie(createCookie("refresh", refresh, jwtTimeComponent.getRefreshExpiration()));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void addRefreshEntity(String username, String refresh, Long refreshExpireTime) {
        // 기존에 존재하는 Refresh 토큰 삭제
        refreshTokenRepository.findByUsername(username).ifPresent(existedToken -> {
            log.info("[addRefreshEntity] 기존 Refresh 토큰 삭제");
            refreshTokenRepository.deleteById(existedToken.getId());
        });

        RefreshEntity refreshEntity = RefreshEntity.builder()
                .username(username)
                .token(refresh)
                .ttl(refreshExpireTime)
                .build();

        refreshTokenRepository.save(refreshEntity);
    }

    private Cookie createCookie(String key, String value, Long refreshExpireTime) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Math.toIntExact(refreshExpireTime / 1000));
        //cookie.setPath("/");
        return cookie;
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        log.info("[successfulAuthentication] 로그인 실패");
        response.setStatus(401);
    }
}