package inu.spp.cryptocoffee.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@RedisHash(value = "refreshToken")
public class RefreshEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Indexed
    @Column(nullable = false)
    private String token;

    @TimeToLive
    @Column(nullable = false)
    private Long expiration;

}
