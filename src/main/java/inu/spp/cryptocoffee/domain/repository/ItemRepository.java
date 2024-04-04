package inu.spp.cryptocoffee.domain.repository;

import inu.spp.cryptocoffee.domain.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}