package inu.spp.cryptocoffee.auth.user.service;

import inu.spp.cryptocoffee.domain.company.entity.CompanyEntity;
import inu.spp.cryptocoffee.domain.company.repository.CompanyRepository;
import inu.spp.cryptocoffee.auth.user.dto.UserJoinRequestDto;
import inu.spp.cryptocoffee.auth.user.dto.UserRoleEnum;
import inu.spp.cryptocoffee.auth.user.entity.UserEntity;
import inu.spp.cryptocoffee.auth.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JoinService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, CompanyRepository companyRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(UserJoinRequestDto userJoinRequestDto) {
        log.info("[joinProcess] joinDto = {}, {}, {}", userJoinRequestDto.getUsername(), userJoinRequestDto.getPassword(), userJoinRequestDto.getName());

        String username = userJoinRequestDto.getUsername();
        String password = userJoinRequestDto.getPassword();
        String company = userJoinRequestDto.getCompany();

        if (userRepository.existsByUsername(username)) {
            return;
        }

        CompanyEntity companyEntity = companyRepository.findByName(company).orElseThrow(()
                -> new IllegalArgumentException("company is not exist"));

        UserEntity data = UserEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .name(userJoinRequestDto.getName())
                .company(companyEntity)
                .role(UserRoleEnum.ROLE_ADMIN) // default role 추후 변경 필요
                .build();

        userRepository.save(data);
    }
}