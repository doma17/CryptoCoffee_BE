package inu.spp.cryptocoffee.auth.user.controller;

import inu.spp.cryptocoffee.auth.user.dto.UserJoinRequest;
import inu.spp.cryptocoffee.auth.user.service.JoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Join API")
@RestController
public class JoinController {

    private final JoinService joinService;

    @Operation(summary = "회원가입")
    @PostMapping("/api/users/join")
    public ResponseEntity<?> joinProcess(@Valid @RequestBody UserJoinRequest userJoinRequest) {

        log.info("[joinProcess] username: {}", userJoinRequest.getUsername());
        // !! 기본적으로 ROLE_ADMIN 발급 !!
        joinService.joinProcess(userJoinRequest);

        return ResponseEntity.ok().build();
    }
}