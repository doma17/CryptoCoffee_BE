package inu.spp.cryptocoffee.domain.order.repository;

import inu.spp.cryptocoffee.domain.company.entity.CompanyEntity;
import inu.spp.cryptocoffee.domain.order.dto.OrderResponseDto;
import inu.spp.cryptocoffee.domain.order.entity.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByCompany(CompanyEntity company, Pageable pageable);
}
