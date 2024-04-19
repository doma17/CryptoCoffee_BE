package inu.spp.cryptocoffee.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDto {
    @NotNull(message = "username is null")
    @NotEmpty(message = "username is empty")
    @Schema(description = "유저의 아이디", example = "test")
    private String username;

    @NotNull(message = "password is null")
    @NotEmpty(message = "password is empty")
    private String password;

    @NotNull(message = "name is null")
    @NotEmpty(message = "name is empty")
    private String name;

    @NotNull(message = "company is null")
    @NotEmpty(message = "company is empty")
    private String company;
}
