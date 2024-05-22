package inu.spp.cryptocoffee.domain.company.controller;

import inu.spp.cryptocoffee.domain.company.dto.CompanyRequestDto;
import inu.spp.cryptocoffee.domain.company.dto.CompanyResponseDto;
import inu.spp.cryptocoffee.domain.company.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/company")
@RestController
@Tag(name = "Company API")
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "회사 등록")
    @PostMapping
    public CompanyResponseDto createCompany(@Valid @RequestBody CompanyRequestDto companyRequestDto) {
        return companyService.createCompany(companyRequestDto);
    }

    @Operation(summary = "회사 목록 조회")
    @GetMapping
    public List<CompanyResponseDto> getCompanies(
            @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size) {
        return companyService.getCompanies(pageNo, size);
    }

    @Operation(summary = "회사 이름만 조회")
    @GetMapping("/name")
    public List<String> getCompanyName() {
        return companyService.getCompaniesNames();
    }
}