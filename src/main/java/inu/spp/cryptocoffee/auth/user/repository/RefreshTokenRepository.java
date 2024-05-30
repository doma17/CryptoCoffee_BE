package inu.spp.cryptocoffee.auth.user.repository;

import inu.spp.cryptocoffee.auth.user.entity.RefreshEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshEntity, Long > {

    Boolean existsByToken(String token);

    Optional<RefreshEntity> findByToken(String token);

    Optional<RefreshEntity> findByUsername(String username);

}
