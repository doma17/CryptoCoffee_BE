package inu.spp.cryptocoffee.auth.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinRequest {

    @Schema(description = "유저의 아이디", example = "test")
    @NotNull(message = "username is null")
    @NotEmpty(message = "username is empty")
    private String username;

    @Schema(description = "유저의 비밀번호", example = "test1234")
    @NotNull(message = "password is null")
    @NotEmpty(message = "password is empty")
    private String password;

    @Schema(description = "유저의 이름", example = "홍길동")
    @NotNull(message = "name is null")
    @NotEmpty(message = "name is empty")
    private String name;

    @Schema(description = "유저의 회사", example = "CrpytoCoffee")
    @NotNull(message = "company is null")
    @NotEmpty(message = "company is empty")
    private String company;
}
