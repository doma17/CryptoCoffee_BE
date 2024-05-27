package inu.spp.cryptocoffee.domain.member.service;

import inu.spp.cryptocoffee.auth.email.dto.EmailMessageDto;
import inu.spp.cryptocoffee.auth.email.entity.EmailAuthEntity;
import inu.spp.cryptocoffee.auth.email.repository.EmailAuthRepository;
import inu.spp.cryptocoffee.auth.email.service.EmailService;
import inu.spp.cryptocoffee.auth.user.dto.CustomUserDetails;
import inu.spp.cryptocoffee.auth.user.entity.UserEntity;
import inu.spp.cryptocoffee.auth.user.repository.UserRepository;
import inu.spp.cryptocoffee.domain.company.entity.CompanyEntity;
import inu.spp.cryptocoffee.domain.company.repository.CompanyRepository;
import inu.spp.cryptocoffee.domain.member.dto.MemberJoinRequestDto;
import inu.spp.cryptocoffee.domain.member.dto.MemberJoinResponseDto;
import inu.spp.cryptocoffee.domain.member.dto.MemberStatus;
import inu.spp.cryptocoffee.domain.member.entity.MemberEntity;
import inu.spp.cryptocoffee.domain.member.repository.MemberRepositroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final UserRepository userRepository;
    private final MemberRepositroy memberRepositroy;
    private final CompanyRepository companyRepository;
    private final EmailAuthRepository emailAuthRepository;
    private final EmailService emailService;

    public List<MemberJoinResponseDto> getRequestingMembers(CustomUserDetails customUserDetails) {
        log.info("[getRequestingMembers] start");
        CompanyEntity company = getCompany(customUserDetails);

        // 회원 정보를 통해 회원들을 조회
        return memberRepositroy.findByCompanyAndStatus(company, MemberStatus.INACTIVE).stream()
                .map(MemberJoinResponseDto::from)
                .collect(Collectors.toList());
    }

    public void createMember(MemberJoinRequestDto memberJoinRequestDto) {
        log.info("[createMember] start");
        // !! 요청 유저의 Email 인증 여부 확인 로직 추가 필요 !!
        EmailAuthEntity emailAuth = emailAuthRepository.findByEmail(memberJoinRequestDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("해당 이메일로 인증을 진행해주세요.")
        );
        if (!emailAuth.isAuth()) {
            throw new IllegalArgumentException("해당 이메일로 인증을 진행해주세요.");
        }

        // 회사 이름을 통해 회사 정보를 조회
        CompanyEntity company = companyRepository.findByName(memberJoinRequestDto.getCompanyName())
                .orElseThrow(() -> new IllegalArgumentException("해당 회사가 존재하지 않습니다."));

        MemberEntity member = MemberJoinRequestDto.toEntity(memberJoinRequestDto, company);
        memberRepositroy.save(member);
    }

    public void rejectMember(CustomUserDetails customUserDetails, String memberEmail) {
        log.info("[rejectMember] start");
        changeMemberEntityStatus(customUserDetails, memberEmail, MemberStatus.REJECT);
    }

    public void acceptMember(CustomUserDetails customUserDetails, String memberEmail) {
        log.info("[acceptMember] start");
        changeMemberEntityStatus(customUserDetails, memberEmail, MemberStatus.ACTIVE);
    }

    public void changeMemberEntityStatus(CustomUserDetails customUserDetails, String memberEmail, MemberStatus status) {
        log.info("[changeMemberEntityStatus] start");
        CompanyEntity company = getCompany(customUserDetails);

        MemberEntity member = memberRepositroy.findByEmailAndCompany(memberEmail, company).orElseThrow(
                () -> new IllegalArgumentException("해당 이메일을 가진 회사가 없습니다.")
        );

        EmailMessageDto emailMessageDto = EmailMessageDto.builder()
                .to(member.getEmail())
                .subject("[CryptoCoffee] 인증 발급 완료")
                .build();

        emailService.sendDidAcceptEmail(emailMessageDto);
        member.changeStatus(status);
        memberRepositroy.save(member);
    }

    public List<MemberJoinResponseDto> getRecentActiveMembers(CustomUserDetails customUserDetails) {
        log.info("[getRecentActiveMembers] start");
        return getMemberJoinResponseDtoListPage(customUserDetails, 0, 5, "createdAt");
    }

    public List<MemberJoinResponseDto> getApprovedMembers(CustomUserDetails customUserDetails, int pageNum, int pageSize, String criteria) {
        log.info("[getApprovedMembers] start");
        return getMemberJoinResponseDtoListPage(customUserDetails, pageNum, pageSize, criteria);
    }

    private List<MemberJoinResponseDto> getMemberJoinResponseDtoListPage(CustomUserDetails customUserDetails, int pageNum, int pageSize, String criteria) {
        log.info("[getMemberJoinResponseDtoListPage] start");
        CompanyEntity company = getCompany(customUserDetails);
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, criteria));
        return memberRepositroy.findByCompanyAndStatus(company, MemberStatus.ACTIVE, pageable).stream()
                .map(MemberJoinResponseDto::from)
                .collect(Collectors.toList());
    }

    // userdetails에서 직접적으로 company를 가져오지 못하는 문제 발생
    private CompanyEntity getCompany(CustomUserDetails customUserDetails) {
        UserEntity user = customUserDetails.getUserEntity();

        log.info("[getRequestingMembers] user : {}", user.getUsername());
        user = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        log.info("[getRequestingMembers] company : {}", user.getCompany().getName());
        return user.getCompany();
    }
}
