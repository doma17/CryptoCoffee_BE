package inu.spp.cryptocoffee.domain.item.dto;

import inu.spp.cryptocoffee.domain.item.entity.CategoryEntity;
import inu.spp.cryptocoffee.domain.item.entity.ItemEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCreateRequestDto {

    private String name;
    private String description;
    private int price;
    private String categoryName;

    public static ItemEntity from(ItemCreateRequestDto itemCreateRequestDto, CategoryEntity category) {
        return ItemEntity.builder()
                .name(itemCreateRequestDto.getName())
                .description(itemCreateRequestDto.getDescription())
                .category(category)
                .build();
    }
}
