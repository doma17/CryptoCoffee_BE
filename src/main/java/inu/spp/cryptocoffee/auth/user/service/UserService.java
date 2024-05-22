package inu.spp.cryptocoffee.auth.user.service;

import inu.spp.cryptocoffee.auth.user.entity.UserEntity;
import inu.spp.cryptocoffee.auth.user.jwt.JwtUtil;
import inu.spp.cryptocoffee.auth.user.repository.UserRepository;
import inu.spp.cryptocoffee.auth.user.controller.UserAuthorityRequestDto;
import inu.spp.cryptocoffee.auth.user.controller.UserPageResponseDto;
import inu.spp.cryptocoffee.domain.company.entity.CompanyEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Transactional
    public List<UserPageResponseDto> getUsers(int pageNo, String criteria) {


        Pageable pageable = PageRequest.of(pageNo, 10, Sort.by(Sort.Direction.DESC, criteria));
        Page<UserPageResponseDto> page = userRepository.findAll(pageable).map(UserPageResponseDto::from);
        return page.getContent();
    }

    private CompanyEntity getCompanyEntity(String token) {
        String username = jwtUtil.getUsername(token);
        return userRepository.findByUsername(username).map(UserEntity::getCompany).orElseThrow(
                () -> new IllegalArgumentException("회사 정보를 찾을 수 없습니다.")
        );
    }

    @Transactional
    public void changeAuthority(UserAuthorityRequestDto userAuthorityRequestDto) {
        log.info("[changeAuthority] userAuthorityRequest : [{}]", userAuthorityRequestDto.getUserId());
        userRepository.findById(userAuthorityRequestDto.getUserId())
                .ifPresent(user -> {
                    user.changeRole(userAuthorityRequestDto.getRole());
                });
    }
}
