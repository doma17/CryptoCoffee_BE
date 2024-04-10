package inu.spp.cryptocoffee.email.controller;

import inu.spp.cryptocoffee.email.dto.EmailAuthDto;
import inu.spp.cryptocoffee.email.dto.EmailMessage;
import inu.spp.cryptocoffee.email.dto.EmailPostDto;
import inu.spp.cryptocoffee.email.dto.EmailResponseDto;
import inu.spp.cryptocoffee.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1/api/email")
@Tag(name = "Email API")
@RestController
public class EmailController {

    private final EmailService emailService;

    @Operation(summary = "회원가입 이메일 인증")
    @PostMapping
    public ResponseEntity<?> sendJoinMail(@Validated @RequestBody EmailPostDto emailPostDto) {

        EmailMessage emailMessage = EmailMessage.builder()
                .to(emailPostDto.getEmail())
                .subject("[CryptoCoffee] 이메일 인증을 위한 인증 코드 발송")
                .build();

        emailService.sendMail(emailMessage, "email");

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이메일 인증 코드 확인")
    @PostMapping("/auth")
    public ResponseEntity<?> sendAuthMail(@RequestBody EmailAuthDto emailAuthDto) {

        if (emailService.checkAuthNum(emailAuthDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}