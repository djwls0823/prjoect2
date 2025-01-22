package com.green.attaparunever2.user;

import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.user.model.UserSignInReq;
import com.green.attaparunever2.user.model.UserSignInRes;
import com.green.attaparunever2.user.model.UserSignUpReq;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final JavaMailSender mailSender;
    private final UserMapper userMapper;
    // 회원가입
    @Transactional
    public ResultResponse<Integer> signUp(UserSignUpReq req) {
        String statusCode = "400";
        String resultMsg = "회원 가입에 실패했습니다.";

        // 비밀번호 암호화
        req.setUpw(BCrypt.hashpw(req.getUpw(), BCrypt.gensalt()));

        int result =  userMapper.insUser(req);

        if(result != 0) {
            statusCode = "200";
            resultMsg = "회원 가입이 완료됐습니다.";
        }

        // 이메일 전송(실패시 유효하지 않은 이메일이다)
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("jumoney1012@gmail.com");
            helper.setTo(req.getEmail());
            helper.setSubject("아따 빠르네 인증 메일 입니다.");
            helper.setText("테스트 중입니다.", false);

            mailSender.send(mimeMessage); // 실제 이메일 전송
        } catch (Exception e) {
            throw new CustomException("이메일 유효성 검사 실패: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResultResponse.<Integer>builder()
                .statusCode(statusCode)
                .resultMsg(resultMsg)
                .resultData(result)
                .build();
    }

    // 로그인
    public ResultResponse<UserSignInRes> signIn(UserSignInReq p) {
        String statusCode = "400";
        String resultMsg;

        UserSignInRes res = userMapper.selUserByUid(p.getUid());

        if(res == null || !BCrypt.checkpw(p.getUpw(),res.getUpw())) {
            res = new UserSignInRes();
            resultMsg = "아이디 혹은 비밀번호를 확인해 주세요.";
        } else {
            statusCode = "200";
            resultMsg = "로그인 성공";
        }

        return ResultResponse.<UserSignInRes>builder()
                .statusCode(statusCode)
                .resultMsg(resultMsg)
                .resultData(res)
                .build();
    }
}
