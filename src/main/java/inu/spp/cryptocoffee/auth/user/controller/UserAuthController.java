package inu.spp.cryptocoffee.auth.user.controller;

import inu.spp.cryptocoffee.auth.user.dto.LoginRequestDto;
import inu.spp.cryptocoffee.auth.user.dto.UserJoinRequestDto;
import inu.spp.cryptocoffee.auth.user.service.JoinService;
import inu.spp.cryptocoffee.auth.user.service.ReissueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Auth API")
@RestController
public class UserAuthController {

    private final JoinService joinService;
    private final ReissueService reissueService;

    @Operation(summary = "로그인")
    @PostMapping("/api/users/login")
    public ResponseEntity<?> login(@RequestParam LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/api/users/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원가입")
    @PostMapping("/api/users/join")
    public ResponseEntity<?> joinProcess(@Valid @RequestBody UserJoinRequestDto userJoinRequestDto) {

        log.info("[joinProcess] username: {}", userJoinRequestDto.getUsername());
        // !! 기본적으로 ROLE_ADMIN 발급 !!
        joinService.joinProcess(userJoinRequestDto);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Refresh Token 재발급")
    @PostMapping("/api/users/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        return reissueService.reissueRefreshToken(request, response);
    }
}