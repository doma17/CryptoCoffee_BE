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
@Tag(name = "Member API - 로그인 후 사용가능 (Notion 참고)")
@RestController
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 가입 요청 중인 회원 조회")
    @GetMapping("/request/list")
    public ResponseEntity<List<MemberJoinResponseDto>> getRequestingMembers(@AuthenticationPrincipal CustomUserDetails customUserDetails) throws Exception{
        List<MemberJoinResponseDto> response = memberService.getRequestingMembers(customUserDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "회원 가입 요청")
    @PostMapping("/request")
    public ResponseEntity<?> createMember(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody MemberJoinRequestDto memberJoinRequestDto
    ) throws Exception {
        memberService.createMember(customUserDetails, memberJoinRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
