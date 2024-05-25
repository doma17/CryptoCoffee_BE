package inu.spp.cryptocoffee.domain.member.service;

import inu.spp.cryptocoffee.auth.email.entity.EmailAuthEntity;
import inu.spp.cryptocoffee.auth.email.repository.EmailAuthRepository;
import inu.spp.cryptocoffee.auth.user.dto.CustomUserDetails;
import inu.spp.cryptocoffee.auth.user.entity.UserEntity;
import inu.spp.cryptocoffee.domain.company.entity.CompanyEntity;
import inu.spp.cryptocoffee.domain.company.repository.CompanyRepository;
import inu.spp.cryptocoffee.domain.member.dto.MemberJoinRequestDto;
import inu.spp.cryptocoffee.domain.member.dto.MemberJoinResponseDto;
import inu.spp.cryptocoffee.domain.member.dto.MemberStatus;
import inu.spp.cryptocoffee.domain.member.entity.MemberEntity;
import inu.spp.cryptocoffee.domain.member.repository.MemberRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepositroy memberRepositroy;
    private final CompanyRepository companyRepository;
    private final EmailAuthRepository emailAuthRepository;

    @Transactional
    public List<MemberJoinResponseDto> getRequestingMembers(CustomUserDetails customUserDetails) {

        // 로그인 중인 유저의 userId를 가져옴
        UserEntity user = customUserDetails.getUserEntity();

        // 회원 정보를 통해 회원들을 조회
        return memberRepositroy.findByCompanyAndStatus(user.getCompany(), MemberStatus.INACTIVE).stream()
                .map(MemberJoinResponseDto::from)
                .collect(Collectors.toList());
    }

    public void createMember(MemberJoinRequestDto memberJoinRequestDto) {

        // !! 요청 유저의 Email 인증 여부 확인 로직 추가 필요 !!

        // 회사 이름을 통해 회사 정보를 조회
        CompanyEntity company = companyRepository.findByName(memberJoinRequestDto.getCompanyName())
                .orElseThrow(() -> new IllegalArgumentException("해당 회사가 존재하지 않습니다."));

        MemberEntity member = MemberJoinRequestDto.toEntity(memberJoinRequestDto, company);
        memberRepositroy.save(member);
    }

    @Transactional
    public void rejectMember(CustomUserDetails customUserDetails, Long memberId) {
        changeMemberEntityStatus(customUserDetails, memberId, MemberStatus.REJECT);
    }

    @Transactional
    public void acceptMember(CustomUserDetails customUserDetails, Long memberId) {
        changeMemberEntityStatus(customUserDetails, memberId, MemberStatus.ACTIVE);
    }

    @Transactional
    public void changeMemberEntityStatus(CustomUserDetails customUserDetails, Long memberId, MemberStatus status) {
        UserEntity user = customUserDetails.getUserEntity();

        if (!memberRepositroy.existsByMemberIdAndCompany(memberId, user.getCompany()))
            throw new IllegalArgumentException("해당 회사에 가입 요청을 한 해당 아이디의 회원이 존재하지 않습니다.");

        MemberEntity member = memberRepositroy.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        if (member.getCompany().eqaual(user.getCompany()))
            throw new IllegalArgumentException("해당 회원이 회사에 속해있지 않습니다.");
        member.changeStatus(status);
    }

    public List<MemberJoinResponseDto> getRecentActiveMembers(CustomUserDetails customUserDetails) {
        return getMemberJoinResponseDtoListPage(customUserDetails, 0, 5, "createdAt");
    }

    public List<MemberJoinResponseDto> getApprovedMembers(CustomUserDetails customUserDetails, int pageNum, int pageSize, String criteria) {
        return getMemberJoinResponseDtoListPage(customUserDetails, pageNum, pageSize, criteria);
    }

    private List<MemberJoinResponseDto> getMemberJoinResponseDtoListPage(CustomUserDetails customUserDetails, int pageNum, int pageSize, String criteria) {
        CompanyEntity company = customUserDetails.getUserEntity().getCompany();
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, criteria));
        return memberRepositroy.findByCompanyAndStatus(company, MemberStatus.ACTIVE, pageable).stream()
                .map(MemberJoinResponseDto::from)
                .collect(Collectors.toList());
    }
}
