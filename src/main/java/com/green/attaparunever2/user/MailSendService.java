package com.green.attaparunever2.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailSendService {
    private final JavaMailSender mailSender;
    LocalDateTime expireTimes = LocalDateTime.now().plusMinutes(10);

    //인증키 생성
    public String getKey(int size) {
        return generateAuthCode(size);
    }

    public String generateAuthCode(int size) {
        SecureRandom random = new SecureRandom();
        StringBuilder buffer = new StringBuilder();
        while(buffer.length() < size) {
            int num = random.nextInt(10);  // 0-9 범위의 난수 생성
            buffer.append(num);
        }
        return buffer.toString();
    }

    //인증메일 보내기
    public boolean sendAuthMail(String email, long userId, String authCode) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("jumoney1012@gmail.com");
            helper.setTo(email);
            helper.setSubject("아따 빠르네 인증 메일 입니다.");
            helper.setText(new StringBuffer()
                    .append("<h1>[이메일 인증]</h1>")
                    .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                    .append("<a href='http://localhost:8080/api/user/auth-token?userId=")
                    .append(userId)
                    .append("&token=")
                    .append(authCode)
                    .append("' target='_blank'>이메일 인증 확인 (클릭)</a>")
                    .append("</div>")
                    .append("<div class='footer'>")
                    .append("<p>감사합니다.<br>관리자 드림</p>")
                    .append("</div>")
                    .toString(), true);
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            // 로깅 처리 추가 (개선)
            log.error("이메일 전송 실패: " + e.getMessage());
            return false;
        }
    }
}
