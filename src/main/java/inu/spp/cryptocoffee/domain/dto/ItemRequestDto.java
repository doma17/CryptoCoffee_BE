package inu.spp.cryptocoffee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {
    @NotNull(message = "item name can't be null")
    @NotEmpty(message = "item name can't be empty")
    @Schema(description = "상품명", example = "아메리카노")
    private String name;

    @NotNull(message = "description name can't be null")
    @NotEmpty(message = "description name can't be empty")
    @Schema(description = "상품 설명", example = "이 커피는 맛있다")
    private String description;

    @NotNull(message = "categoryName name can't be null")
    @NotEmpty(message = "categoryName name can't be empty")
    @Schema(description = "상품 카테고리", example = "커피")
    private String categoryName;
}
