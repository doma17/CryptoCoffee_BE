package inu.spp.cryptocoffee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    @NotNull(message = "itemId name is null")
    @NotEmpty(message = "itemId name is empty")
    @Schema(description = "주문한 Item의 아이디", example = "1")
    private Long itemId;

    @NotNull(message = "itemId name is null")
    @NotEmpty(message = "itemId name is empty")
    @Schema(description = "주문한 유저의 아이디", example = "1")
    private Long userId;
}