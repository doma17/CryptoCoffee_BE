package inu.spp.cryptocoffee.email.controller;

import inu.spp.cryptocoffee.email.dto.EmailAuthRequest;
import inu.spp.cryptocoffee.email.dto.EmailMessage;
import inu.spp.cryptocoffee.email.dto.EmailPostRequest;
import inu.spp.cryptocoffee.email.service.EmailService;
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
@RequestMapping("/api/email")
@Tag(name = "Email API")
@RestController
public class EmailController {

    private final EmailService emailService;

    @Operation(summary = "회원가입 이메일 인증")
    @PostMapping
    public ResponseEntity<?> sendJoinMail(@Valid @RequestBody EmailPostRequest emailPostRequest) {

        EmailMessage emailMessage = EmailMessage.builder()
                .to(emailPostRequest.getEmail())
                .subject("[CryptoCoffee] 이메일 인증을 위한 인증 코드 발송")
                .build();

        emailService.sendMail(emailMessage, "email");

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이메일 인증 코드 확인")
    @PostMapping("/auth")
    public ResponseEntity<?> sendAuthMail(@Valid @RequestBody EmailAuthRequest emailAuthRequest) {

        if (emailService.checkAuthNum(emailAuthRequest)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
