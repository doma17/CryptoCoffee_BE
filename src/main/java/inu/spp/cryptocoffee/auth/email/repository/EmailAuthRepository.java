package inu.spp.cryptocoffee.auth.email.repository;

import inu.spp.cryptocoffee.auth.email.entity.EmailAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAuthRepository extends JpaRepository<EmailAuthEntity, Long> {

    EmailAuthEntity findByEmail(String email);

    Boolean existsByEmail(String email);

}
