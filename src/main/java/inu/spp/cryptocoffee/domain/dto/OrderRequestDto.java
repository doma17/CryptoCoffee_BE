package inu.spp.cryptocoffee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {

    @Schema(description = "주문한 Item의 아이디", example = "1")
    @NotEmpty(message = "상품 아이디를 입력해주세요.")
    private Long itemId;

    /**
     * 유저를 인증한 상태에서만 접근 가능하고,
     * 추후 유저의 JWT 토큰을 통해 유저의 ID를 받아온다.
     */
    @Schema(description = "주문한 유저의 아이디", example = "1")
    @NotEmpty(message = "유저 아이디를 입력해주세요.")
    private Long userId;
}