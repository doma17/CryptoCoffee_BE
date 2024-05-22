package inu.spp.cryptocoffee.auth.email.controller;

import inu.spp.cryptocoffee.auth.email.dto.EmailAuthRequestDto;
import inu.spp.cryptocoffee.auth.email.dto.EmailPostRequestDto;
import inu.spp.cryptocoffee.auth.email.dto.EmailMessageDto;
import inu.spp.cryptocoffee.auth.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/users/email")
@Tag(name = "Email API")
@RestController
public class EmailController {

    private final EmailService emailService;

    @Operation(summary = "회원가입 이메일 인증")
    @PostMapping
    public ResponseEntity<?> sendJoinMail(@Valid @RequestBody EmailPostRequestDto emailPostRequestDto) {

        EmailMessageDto emailMessageDto = EmailMessageDto.builder()
                .to(emailPostRequestDto.getEmail())
                .subject("[CryptoCoffee] 이메일 인증을 위한 인증 코드 발송")
                .build();

        emailService.sendMail(emailMessageDto, "email");

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이메일 인증 코드 확인")
    @PostMapping("/auth")
    public ResponseEntity<?> sendAuthMail(@Valid @RequestBody EmailAuthRequestDto emailAuthRequest) {

        if (emailService.checkAuthNum(emailAuthRequest)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
