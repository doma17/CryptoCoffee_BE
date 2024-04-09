package inu.spp.cryptocoffee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {
    @NotEmpty(message = "상품명을 입력해주세요.")
    @Schema(description = "상품명", example = "아메리카노")
    private String name;

    @NotEmpty(message = "상품 설명을 입력해주세요.")
    @Schema(description = "상품 설명", example = "이 커피는 맛있다")
    private String description;

    @NotEmpty(message = "상품 카테고리를 입력해주세요.")
    @Schema(description = "상품 카테고리", example = "커피")
    private String categoryName;
}
