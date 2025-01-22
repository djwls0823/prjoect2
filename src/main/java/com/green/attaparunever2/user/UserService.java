package com.green.attaparunever2.user;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.user.model.UserSignInReq;
import com.green.attaparunever2.user.model.UserSignInRes;
import com.green.attaparunever2.user.model.UserSignUpReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    // 회원가입
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
