package inu.spp.cryptocoffee.email.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailResponseDto {

    @NotNull
    private String code;

}
