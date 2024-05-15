package inu.spp.cryptocoffee.domain.repository;

import inu.spp.cryptocoffee.domain.entity.CategoryEntity;
import inu.spp.cryptocoffee.domain.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query("select i from ItemEntity i where i.category = :category")
    List<ItemEntity> findByCategory(CategoryEntity category);
}