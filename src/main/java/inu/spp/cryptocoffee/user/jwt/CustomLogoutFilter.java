package inu.spp.cryptocoffee.user.jwt;

import inu.spp.cryptocoffee.user.repository.RefreshTokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class CustomLogoutFilter extends GenericFilterBean {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    /**
     * 로그아웃 필터
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        // Logout Request 검증
        if (verifiedLogoutRequest(request, response, filterChain)) return;

        // Cookie에서 Refresh Token 가져오기
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            doStatusBadRequest("[doFilter] cookie is null", response);
            return;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }
        // Refresh Token 검증
        if (!validateRefreshToken(response, refresh)) return;

        //로그아웃 진행
        log.info("[doFilter] 로그아웃 진행");
        //Refresh 토큰 DB에서 제거
        refreshTokenRepository.findByToken(refresh).ifPresent(refreshTokenRepository::delete);

        //Refresh 토큰 Cookie 값 0
        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * 로그아웃 요청 검증
     * @param request
     * @param response
     * @param filterChain
     * @return boolean
     * @throws IOException
     * @throws ServletException
     */
    private static boolean verifiedLogoutRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // logout 요청이 아닌 경우
        final String requestUri = request.getRequestURI();
        if (!requestUri.matches("^\\/logout$")) {
            filterChain.doFilter(request, response);
            return true;
        }
        // logout 요청이 POST가 아닌 경우
        final String requestMethod = request.getMethod();
        if (!requestMethod.equals("POST")) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }

    /**
     * Refresh Token 검증
     * @param response
     * @param refresh
     * @return
     */
    private boolean validateRefreshToken(HttpServletResponse response, String refresh) {
        // refresh token이 없는 경우
        if (refresh == null) {
            doStatusBadRequest("[doFilter] refresh is null", response);
            return false;
        }
        // 토큰 만료 검증
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            doStatusBadRequest("[doFilter] refresh is expired", response);
            return false;
        }
        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            //response status code
            doStatusBadRequest("[doFilter] token is not refresh token", response);
            return false;
        }
        //DB에 저장되어 있는지 확인
        if (!refreshTokenRepository.existsByToken(refresh)) {
            //response status code
            doStatusBadRequest("[doFilter] refresh token not exist in Repository", response);
            return false;
        }
        return true;
    }

    private static void doStatusBadRequest(String msg, HttpServletResponse response) {
        log.info(msg);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}