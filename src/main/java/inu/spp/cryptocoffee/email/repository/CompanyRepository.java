package inu.spp.cryptocoffee.email.repository;

import inu.spp.cryptocoffee.email.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    Boolean existsByName(String name);
    Optional<CompanyEntity> findByName(String name);
}
