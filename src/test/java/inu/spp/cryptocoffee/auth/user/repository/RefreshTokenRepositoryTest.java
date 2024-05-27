package inu.spp.cryptocoffee.auth.user.repository;

import groovy.util.logging.Slf4j;
import inu.spp.cryptocoffee.auth.user.entity.RefreshEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
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
                .expiration(expiration)
                .build();
    }

    @Test
    public void 토큰제거테스트() {
        RefreshEntity savedRefresh = refreshTokenRepository.save(refresh);

        assertTrue(refreshTokenRepository.findById(savedRefresh.getId()).isPresent());

        refreshTokenRepository.findById(savedRefresh.getId()).ifPresent(token -> {
            refreshTokenRepository.deleteById(token.getId());
        });

        assertFalse(refreshTokenRepository.findById(savedRefresh.getId()).isPresent());
    }

    @Test
    void existsByToken() {
    }

    @Test
    void findByToken() {
    }

    @Test
    void findByUsername() {
    }
}