package inu.spp.cryptocoffee.auth.email.service;

import inu.spp.cryptocoffee.auth.email.dto.EmailAuthRequestDto;
import inu.spp.cryptocoffee.auth.email.entity.EmailAuthEntity;
import inu.spp.cryptocoffee.auth.email.repository.EmailAuthRepository;
import inu.spp.cryptocoffee.auth.email.dto.EmailMessageDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;
    private final EmailAuthRepository emailAuthRepository;

    /**
     * 메일 전송 메서드
     *
     * @param emailMessageDto
     * @param type
     */
    public void sendAuthMail(EmailMessageDto emailMessageDto, String type) {
        log.info("[sendAuthMail] start, email : {}", emailMessageDto.getTo());
        String authNum = createCode(); // 인증 코드 생성
        log.info("[sendAuthMail] authNum : {}", authNum);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        // DB에 Entity를 저장해서 TTL을 통해 관리..?
        // 이미 인증한 이메일 경우의 예외 처리 추가

        EmailAuthEntity emailAuth = null;
        if (!emailAuthRepository.existsByEmail(emailMessageDto.getTo())) { // 이메일이 존재하지 않는 경우
            log.info("[sendAuthMail] email is not exist");
            emailAuth = EmailAuthEntity.builder()
                    .email(emailMessageDto.getTo())
                    .build();
        } else {    // 이메일이 존재하는 경우 인증번호만 변경 (재인증)
            log.info("[sendAuthMail] email is exist");
            emailAuth = emailAuthRepository.findByEmail(emailMessageDto.getTo()).orElseThrow(
                    () -> new IllegalArgumentException("email is not exist")
            );
        }

        if (emailAuth.isAuth()) { // 이미 인증한 이메일인 경우
            log.info("[sendAuthMail] already auth");
            throw new IllegalArgumentException("already auth");
        }

        sendAuthMail(emailMessageDto, type, mimeMessage, authNum, emailAuth);
    }

    @Async
    public void sendAuthMail(EmailMessageDto emailMessageDto, String type, MimeMessage mimeMessage, String authNum, EmailAuthEntity emailAuth) {
        try {
            log.info("[sendAuthMail] send email");
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessageDto.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(emailMessageDto.getSubject()); // 메일 제목
            mimeMessageHelper.setText(setContext(authNum, type), true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

            log.info("[sendAuthMail] success");
            emailAuth.updateAuthNum(authNum);
            emailAuthRepository.save(emailAuth);

        } catch (MessagingException e) {
            log.info("[sendAuthMail] fail");
            throw new RuntimeException(e);
        }
    }

    @Async
    public void sendDidAcceptEmail(EmailMessageDto emailMessageDto) {
        // 이메일 인증 성공 시
        log.info("[sendDidAcceptEmail] email : {}", emailMessageDto.getTo());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            log.info("[sendDidAcceptEmail] send email");
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessageDto.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(emailMessageDto.getSubject()); // 메일 제목
            mimeMessageHelper.setText(springTemplateEngine.process("email_accept", new Context()), true);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            log.info("[sendDidAcceptEmail] fail");
            throw new RuntimeException(e);
        }
    }

    /**
     * 인증번호 확인 메서드
     * @param emailAuthRequest
     * @return 인증 성공 여부
     */
    public boolean checkAuthNum(EmailAuthRequestDto emailAuthRequest) {

        // 이메일 DB 저장 여부 확인
        if (!emailAuthRepository.existsByEmail(emailAuthRequest.getEmail())) {
            // Custom Throw 제작
            return false;
        }

        EmailAuthEntity emailAuth = emailAuthRepository.findByEmail(emailAuthRequest.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("email is not exist")
        );

        // 인증번호 일치 여부 확인
        if (!emailAuth.getAuthNum().equals(emailAuthRequest.getAuthNum())) {
            // Custom Throw 제작
            return false;
        }

        emailAuth.updateIsAuthTrue(); // 이메일 인증 성공 변환
        emailAuthRepository.save(emailAuth);

        return true;
    }

    /**
     * 인증번호 생성 메서드
     * @return 인증번호
     */
    private String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }

    /**
     * thymeleaf를 통한 html 적용
     * @param code
     * @param type
     * @return html
     */
    private String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return springTemplateEngine.process(type, context);
    }
}
