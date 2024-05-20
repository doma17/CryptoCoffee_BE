package inu.spp.cryptocoffee.auth.user.service;

import inu.spp.cryptocoffee.domain.entity.CompanyEntity;
import inu.spp.cryptocoffee.domain.repository.CompanyRepository;
import inu.spp.cryptocoffee.auth.user.dto.UserJoinRequest;
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

    public void joinProcess(UserJoinRequest userJoinRequest) {
        log.info("[joinProcess] joinDto = {}, {}, {}", userJoinRequest.getUsername(), userJoinRequest.getPassword(), userJoinRequest.getName());

        String username = userJoinRequest.getUsername();
        String password = userJoinRequest.getPassword();
        String company = userJoinRequest.getCompany();

        if (userRepository.existsByUsername(username)) {
            return;
        }

        CompanyEntity companyEntity = companyRepository.findByName(company).orElseThrow(()
                -> new IllegalArgumentException("company is not exist"));

        UserEntity data = UserEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .name(userJoinRequest.getName())
                .company(companyEntity)
                .role(UserRoleEnum.ROLE_ADMIN) // default role 추후 변경 필요
                .build();

        userRepository.save(data);
    }
}