package inu.spp.cryptocoffee.auth.user.controller;

import inu.spp.cryptocoffee.auth.user.dto.UserJoinRequestDto;
import inu.spp.cryptocoffee.auth.user.service.JoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Join API")
@RestController
public class JoinController {

    private final JoinService joinService;

    @Operation(summary = "회원가입")
    @PostMapping("/api/users/join")
    public ResponseEntity<?> joinProcess(@Valid @RequestBody UserJoinRequestDto userJoinRequestDto) {

        log.info("[joinProcess] username: {}", userJoinRequestDto.getUsername());
        // !! 기본적으로 ROLE_ADMIN 발급 !!
        joinService.joinProcess(userJoinRequestDto);

        return ResponseEntity.ok().build();
    }
}