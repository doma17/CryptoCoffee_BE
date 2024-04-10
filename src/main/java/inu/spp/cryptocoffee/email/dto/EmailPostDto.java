package inu.spp.cryptocoffee.email.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EmailPostDto {

    @Email
    @NotNull
    @Schema(description = "받는 사람 이메일 주소", example = "example@gmail.com")
    private String email;

}
