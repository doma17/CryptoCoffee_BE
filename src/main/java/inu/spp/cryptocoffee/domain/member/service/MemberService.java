package inu.spp.cryptocoffee.domain.member.service;

import inu.spp.cryptocoffee.auth.user.dto.CustomUserDetails;
import inu.spp.cryptocoffee.auth.user.entity.UserEntity;
import inu.spp.cryptocoffee.domain.member.dto.MemberJoinResponseDto;
import inu.spp.cryptocoffee.domain.member.dto.MemberStatus;
import inu.spp.cryptocoffee.domain.member.repository.MemberRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepositroy memberRepositroy;

    @Transactional
    public List<MemberJoinResponseDto> getRequestingMembers(CustomUserDetails customUserDetails) {

        // 로그인 중인 유저의 userId를 가져옴
        UserEntity user = customUserDetails.getUserEntity();

        // 회원 정보를 통해 회원들을 조회
        return memberRepositroy.findByCompanyAndStatus(user.getCompany(), MemberStatus.INACTIVE).stream()
                .map(MemberJoinResponseDto::from)
                .collect(Collectors.toList());
    }
}
