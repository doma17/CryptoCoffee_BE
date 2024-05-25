package inu.spp.cryptocoffee.domain.item.dto;

import inu.spp.cryptocoffee.domain.item.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto {

    private String name;

    public static CategoryResponseDto from(CategoryEntity categoryEntity) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.name = categoryEntity.getName();
        return categoryResponseDto;
    }
}
