package inu.spp.cryptocoffee.auth.email.repository;

import inu.spp.cryptocoffee.auth.email.entity.EmailAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailAuthRepository extends JpaRepository<EmailAuthEntity, Long> {

    Optional<EmailAuthEntity> findByEmail(String email);

    Boolean existsByEmail(String email);

}
