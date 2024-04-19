package inu.spp.cryptocoffee.user.service;

import inu.spp.cryptocoffee.email.entity.CompanyEntity;
import inu.spp.cryptocoffee.email.repository.CompanyRepository;
import inu.spp.cryptocoffee.user.dto.JoinDto;
import inu.spp.cryptocoffee.user.entity.UserEntity;
import inu.spp.cryptocoffee.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public void joinProcess(JoinDto joinDto) {

        String username = joinDto.getUsername();
        String password = joinDto.getPassword();
        String company = joinDto.getCompany();

        if (userRepository.existsByUsername(username)) {
            return;
        }

        CompanyEntity companyEntity = companyRepository.findByName(company).orElseThrow(()
                -> new IllegalArgumentException("존재하지 않는 회사명입니다."));

        UserEntity data = UserEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .name(joinDto.getName())
                .company(companyEntity)
                .role(UserRoleEnum.ROLE_ADMIN) // default role 추후 변경 필요
                .build();

        userRepository.save(data);
    }
}