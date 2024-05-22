package inu.spp.cryptocoffee.auth.user.controller;

import inu.spp.cryptocoffee.auth.user.dto.UserRoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserAuthorityRequestDto {
    @Schema(description = "유저의 아이디", example = "1")
    @NotNull(message = "userId is null")
    @NotEmpty(message = "userId is empty")
    private Long userId;

    @Schema(description = "유저의 권한", example = "ROLE_USER")
    @NotNull(message = "role is null")
    @NotEmpty(message = "role is empty")
    private UserRoleEnum role;
}
