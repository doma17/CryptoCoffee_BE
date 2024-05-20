package inu.spp.cryptocoffee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Address {

    @Schema(description = "도시", example = "서울")
    @NotNull(message = "city is null")
    @NotEmpty(message = "city is empty")
    private String city;

    @Schema(description = "거리", example = "강남대로")
    @NotNull(message = "street is null")
    @NotEmpty(message = "street is empty")
    private String street;

    @Schema(description = "우편번호", example = "12345")
    @NotNull(message = "zipcode is null")
    @NotEmpty(message = "zipcode is empty")
    private String zipcode;
}