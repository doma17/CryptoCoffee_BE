package inu.spp.cryptocoffee.domain.item.repository;

import inu.spp.cryptocoffee.domain.item.entity.CategoryEntity;
import inu.spp.cryptocoffee.domain.item.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByCategory(CategoryEntity categoryEntity);

}
