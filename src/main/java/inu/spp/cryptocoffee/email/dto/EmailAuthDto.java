package inu.spp.cryptocoffee.email.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAuthDto {

    @Email
    @NotNull
    @Schema(description = "이메일", example = "example@gmail.com")
    private String email;

    @NotNull
    @Schema(description = "인증번호", example = "123456")
    private String authNum;

}
