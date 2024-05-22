package inu.spp.cryptocoffee.domain.company.service;

import inu.spp.cryptocoffee.domain.company.dto.CompanyRequestDto;
import inu.spp.cryptocoffee.domain.company.dto.CompanyResponseDto;
import inu.spp.cryptocoffee.domain.company.entity.CompanyEntity;
import inu.spp.cryptocoffee.domain.company.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    /**
     * 회사 생성
     * @param companyRequestDto
     * @return CompanyResponseDto
     */
    public CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto) {
        // 회사 생성시 회사 번호, 이름 중복 Validation 로직 추가 필요.

        CompanyEntity company = CompanyEntity.builder()
                .name(companyRequestDto.getName())
                .companyNumber(companyRequestDto.getCompanyNumber())
                .address(companyRequestDto.getAddress())
                .build();

        return CompanyResponseDto.from(companyRepository.save(company));
    }

    /**
     * 회사 목록 조회
     * @param pageNo
     * @param pageSize
     * @return List<CompanyResponseDto>
     */
    @Transactional
    public List<CompanyResponseDto> getCompanies(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<CompanyResponseDto> page = companyRepository.findAll(pageable).map(CompanyResponseDto::from);
        return page.getContent();
    }

    public List<String> getCompaniesNames() {
        return companyRepository.findAll().stream()
                .map(CompanyEntity::getName)
                .toList();
    }
}
