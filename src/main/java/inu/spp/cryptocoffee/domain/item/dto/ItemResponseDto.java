package inu.spp.cryptocoffee.domain.item.dto;

import inu.spp.cryptocoffee.domain.item.entity.ItemEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponseDto {

    private String name;
    private String description;
    private int price;

    public static ItemResponseDto from(ItemEntity itemEntity) {
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        itemResponseDto.name = itemEntity.getName();
        itemResponseDto.description = itemEntity.getDescription();
        itemResponseDto.price = itemEntity.getPrice();
        return itemResponseDto;
    }
}
