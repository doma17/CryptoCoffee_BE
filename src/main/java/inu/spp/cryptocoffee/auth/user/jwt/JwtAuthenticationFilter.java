package inu.spp.cryptocoffee.auth.user.jwt;

import inu.spp.cryptocoffee.auth.user.dto.CustomUserDetails;
import inu.spp.cryptocoffee.auth.user.dto.UserRoleEnum;
import inu.spp.cryptocoffee.auth.user.entity.UserEntity;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    /**
     * JWT 토큰 검증 및 인증 처리
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 요청 IP 로깅
        // !!! IP 관련 내용은 추후 다른 Filter로 추가할 예정 !!!
        log.info("[doFilterInternal] Request IP : {}", request.getRemoteAddr());

        // 토큰 검증
        String accessToken = verifiedAccessToken(request, response, filterChain);
        if (accessToken == null) return;

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(accessToken);
        UserRoleEnum userRole = UserRoleEnum.valueOf(jwtUtil.getRole(accessToken));

        //userEntity를 생성하여 값 set
        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .role(userRole)
                .build();

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    /**
     * JWT Access Token 검증
     * @param request
     * @param response
     * @param filterChain
     * @return access token or null
     * @throws IOException
     * @throws ServletException
     */
    private String verifiedAccessToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final String accessToken = request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            log.debug("[doFilterInternal] not Bearer token");
            filterChain.doFilter(request, response);
            return null;
        }

        // Bearer token 검증
        if (!accessToken.split(" ")[0].equals("Bearer")) {
            log.debug("[doFilter] not Bearer token");
            doStatusUnAuth("[doFilterInternal] not Bearer token", response, "invalid token");
            return null;
        }

        // 토큰 만료 검증
        final String token = accessToken.split(" ")[1];
        try {
            jwtUtil.isExpired(token);
        } catch (ExpiredJwtException e) {
            log.info("[doFilterInternal] access token : {}", token);
            log.info("[doFilterInternal] error message : {}", e.getMessage());
            doStatusUnAuth("[doFilterInternal] access token expired", response, "access token expired");
            return null;
        } catch (Exception e) {
            log.info("[doFilterInternal] access token : {}", token);
            log.info("[doFilterInternal] error message : {}", e.getMessage());
            doStatusUnAuth("[doFilterInternal] access token invalid", response, "invalid access token");
            return null;
        }

        // 토큰 Access 여부 검증
        final String category = jwtUtil.getCategory(token);
        if (!category.equals("access")) {
            doStatusUnAuth("[doFilterInternal] not access token", response, "invalid access token");
            return null;
        }
        return token;
    }

    /**
     * 401 Unauthorized 처리
     * @param msg
     * @param response
     * @param invalid_token
     * @throws IOException
     */
    private static void doStatusUnAuth(String msg, HttpServletResponse response, String invalid_token) throws IOException {
        log.info(msg);
        // 토큰이 유효하지 않을 경우 401 Unauthorized 반환
        // 추후 404 Not Found로 변경 예정 및 메세지 제거
        PrintWriter writer = response.getWriter();
        writer.print(invalid_token);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
