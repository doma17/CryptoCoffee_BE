package inu.spp.cryptocoffee.auth.user.repository;

import groovy.util.logging.Slf4j;
import inu.spp.cryptocoffee.auth.user.entity.RefreshEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:test.yml"})
/**
@TestPropertySource(properties = {
        "DB_URL=jdbc:mariadb://localhost:3307/crypto_coffee?characterEncoding=UTF-8&serverTimezone=UTC",
        "DB_USERNAME=root",
        "DB_PASSWORD=1234",
        "JWT_SECRET=21hr873ho4byaor89h3q278ofb328ue3hoybfy2983ye9hybhefarfgytoz87hcry3hnmzml",
        "MAIL_USERNAME=cryptocoffee.noreply",
        "MAIL_PASSWORD=yzzw evim exaw ogcq",
        "REDIS_HOST=localhost",
        "REDIS_PORT=6379"
})
*/
class RefreshTokenRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(RefreshTokenRepositoryTest.class);
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private RefreshEntity refresh;

    @BeforeEach
    public void setUp() {
        String username = "test-user";
        String token = "test-token";
        Long expiration = 600L;

        refresh = RefreshEntity.builder()
                .username(username)
                .token(token)
                .ttl(expiration)
                .build();
    }



    @Test
    @DisplayName("토큰 ID로 삭제, 검색 테스트")
    public void testById() {
        // given
        RefreshEntity savedRefresh = refreshTokenRepository.save(refresh);
        // when
        assertTrue(refreshTokenRepository.findById(savedRefresh.getId()).isPresent());
        refreshTokenRepository.findById(savedRefresh.getId()).ifPresent(token -> {
            refreshTokenRepository.deleteById(token.getId());
        });
        // then
        assertFalse(refreshTokenRepository.findById(savedRefresh.getId()).isPresent());
    }

    @Test
    @DisplayName("토큰 문자열로 삭제, 검색 테스트")
    public void testByToken() {
        // given
        RefreshEntity savedRefresh = refreshTokenRepository.save(refresh);
        // when
        assertTrue(refreshTokenRepository.findByToken(savedRefresh.getToken()).isPresent());
        refreshTokenRepository.findByToken(savedRefresh.getToken()).ifPresent(token -> {
            refreshTokenRepository.deleteById(token.getId());
        });
        // then
        assertFalse(refreshTokenRepository.findByToken(savedRefresh.getToken()).isPresent());
    }

    @Test
    @DisplayName("토큰 문자열로 삭제, 검색 테스트")
    public void testByUsername() {
        // given
        RefreshEntity savedRefresh = refreshTokenRepository.save(refresh);
        // when
        assertTrue(refreshTokenRepository.findByUsername(savedRefresh.getUsername()).isPresent());
        refreshTokenRepository.findByUsername(savedRefresh.getUsername()).ifPresent(token -> {
            refreshTokenRepository.deleteById(token.getId());
        });
        // then
        assertFalse(refreshTokenRepository.findByUsername(savedRefresh.getUsername()).isPresent());
    }
}