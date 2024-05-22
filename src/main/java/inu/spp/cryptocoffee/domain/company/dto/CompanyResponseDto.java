package inu.spp.cryptocoffee.domain.company.dto;

import inu.spp.cryptocoffee.domain.company.entity.CompanyEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CompanyResponseDto {

    @Schema(description = "회사명", example = "커피회사")
    private String name;

    @Schema(description = "사업자번호", example = "123-45-67890")
    private String companyNumber;

    @Schema(description = "주소")
    @Embedded
    private Address address;

    public static CompanyResponseDto from(CompanyEntity company) {
        CompanyResponseDto companyResponseDto = new CompanyResponseDto();
        companyResponseDto.name = company.getName();
        companyResponseDto.companyNumber = company.getCompanyNumber();
        companyResponseDto.address = company.getAddress();
        return companyResponseDto;
    }
}
