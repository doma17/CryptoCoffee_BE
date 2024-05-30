package inu.spp.cryptocoffee.auth.user.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Setter
@Builder
@RedisHash(value = "refreshToken")
public class RefreshEntity {

    @Id
    private Long id;

    @Indexed
    private String username;

    @Indexed
    private String token;

    @TimeToLive
    private Long ttl;

}
