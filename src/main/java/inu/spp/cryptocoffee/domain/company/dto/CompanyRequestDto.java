package inu.spp.cryptocoffee.domain.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CompanyRequestDto {

    @Schema(description = "회사명", example = "커피회사")
    @NotNull(message = "name is null")
    @NotEmpty(message = "name is empty")
    private String name;

    @Schema(description = "사업자번호", example = "123-45-67890")
    @NotNull(message = "companyNumber is null")
    @NotEmpty(message = "companyNumber is empty")
    private String companyNumber;

    @Schema(description = "주소")
    @NotNull(message = "address is null")
    @NotEmpty(message = "address is empty")
    private Address address;
}
