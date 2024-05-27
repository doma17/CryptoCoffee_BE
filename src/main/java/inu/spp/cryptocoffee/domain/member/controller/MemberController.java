package inu.spp.cryptocoffee.domain.member.controller;

import inu.spp.cryptocoffee.auth.user.dto.CustomUserDetails;
import inu.spp.cryptocoffee.domain.member.dto.MemberJoinRequestDto;
import inu.spp.cryptocoffee.domain.member.dto.MemberJoinResponseDto;
import inu.spp.cryptocoffee.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@Tag(name = "Member API - apiKey 필요")
@RestController
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 가입 요청 중인 회원 조회")
    @GetMapping("/request/list")
    public ResponseEntity<List<MemberJoinResponseDto>> getRequestingMembers(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<MemberJoinResponseDto> response = memberService.getRequestingMembers(customUserDetails);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "회원 가입 요청 - 로그인 인증 X (이메일 인증 필요)")
    @PostMapping("/request")
    public ResponseEntity<?> createMember(
            @RequestBody MemberJoinRequestDto memberJoinRequestDto
    ) {
        memberService.createMember(memberJoinRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "회원 가입 요청 거부")
    @PostMapping("/request/reject")
    public ResponseEntity<?> rejectMember(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam String memberEmail
    ) {
        memberService.rejectMember(customUserDetails, memberEmail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "회원 가입 요청 허가")
    @PostMapping("/request/accept")
    public ResponseEntity<?> acceptMember(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam String memberEmail
    ) {
        memberService.acceptMember(customUserDetails, memberEmail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "가장 최근에 회원 가입된 회원들 조회 - 5명")
    @GetMapping("/recent")
    public ResponseEntity<List<MemberJoinResponseDto>> getRecentMember(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        List<MemberJoinResponseDto> response = memberService.getRecentActiveMembers(customUserDetails);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "허가된 회원 조회 - 페이징")
    @GetMapping("/approved/list")
    public ResponseEntity<List<MemberJoinResponseDto>> getApprovedMembers(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(defaultValue = "0", value = "page") int pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") int pageSize,
            @RequestParam(defaultValue = "createdAt", value = "sort") String criteria
    ) {
        List<MemberJoinResponseDto> response = memberService.getApprovedMembers(customUserDetails, pageNum, pageSize, criteria);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
