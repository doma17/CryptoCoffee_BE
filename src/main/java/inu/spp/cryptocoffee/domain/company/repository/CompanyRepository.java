package inu.spp.cryptocoffee.domain.company.repository;

import inu.spp.cryptocoffee.domain.company.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Boolean existsByName(String name);

    Optional<CompanyEntity> findByName(String name);

    Page<CompanyEntity> findAll(Pageable pageable);

}
