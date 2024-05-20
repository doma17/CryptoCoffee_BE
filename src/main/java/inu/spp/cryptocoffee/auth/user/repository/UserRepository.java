package inu.spp.cryptocoffee.auth.user.repository;

import inu.spp.cryptocoffee.domain.entity.CompanyEntity;
import inu.spp.cryptocoffee.auth.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    Page<UserEntity> findAllByCompany (Pageable pageable, CompanyEntity company);
}
