package com.green.attaparunever2.user;

import com.green.attaparunever2.common.DateTimeUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final MailSendService mailSendService;
    private final UserMapper userMapper;
    // 회원가입
    @Transactional
    public int signUp(UserSignUpReq req) {
        // 인증정보 조회(만약 인증이 만료된 아이디로 가입하려는 경우 인증 정보를 지우고 유저 정보도 지워야 함)
        UserMailVerificationDTO userMailVerificationDTO = userMapper.selUserEmailVerificationByUId(req.getUid());

        if(userMailVerificationDTO != null) {
            LocalDateTime now = LocalDateTime.now();

            // 인증 기간이 만료되었다면 인증, 회원정보 삭제
            if (now.isAfter(DateTimeUtils.convertToLocalDateTime(userMailVerificationDTO.getExpiredDate()))) {
                userMapper.delUserEmailVerification(userMailVerificationDTO.getUserId());
                userMapper.delUser(userMailVerificationDTO.getUserId());
            }
        }

        // 비밀번호 암호화
        req.setUpw(BCrypt.hashpw(req.getUpw(), BCrypt.gensalt()));

        // 회원 생성
        int result =  userMapper.insUser(req);

        /*if(result != 0) {
            // 인증번호 생성
            String authKey = mailSendService.generateAuthCode(10);

            // 인증번호 저장
            userMailVerificationDTO = new UserMailVerificationDTO();
            userMailVerificationDTO.setUserId(req.getUserId());
            userMailVerificationDTO.setToken(authKey);

            result = userMapper.insUserEmailVerification(userMailVerificationDTO);

            if(result == 0) {
                throw new CustomException("회원가입에 실패 했습니다.", HttpStatus.BAD_REQUEST);
            }

            // 인증번호 이메일 전송
            mailSendService.sendAuthMail("/user/auth-token?userId=", req.getEmail(), req.getUserId(), userMailVerificationDTO.getToken());
        }*/

        return result;
    }

    // 회원정보 조회
    public UserGetRes getUser(UserGetReq p) {
        UserGetRes res = userMapper.selUserByUserId(p.getUserId());

        if(res == null) {
            throw new CustomException("회원정보를 불러올 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        return res;
    }

    // 회원삭제
    @Transactional
    public int delUser(UserDelReq p) {
        int result = userMapper.delUser(p.getUserId());

        if(result == 0) {
            throw new CustomException("회원삭제에 실패 했습니다.", HttpStatus.BAD_REQUEST);
        }

        return result;
    }

    // 인증처리
    @Transactional
    public int authToken(AuthTokenReq p) {
        UserMailVerificationDTO userMailVerificationDTO = userMapper.selUserEmailVerificationByUserId(p.getUserId());

        if(userMailVerificationDTO == null) {
            throw new CustomException("인증 정보가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        } else {
            LocalDateTime now = LocalDateTime.now();

            if (now.isAfter(DateTimeUtils.convertToLocalDateTime(userMailVerificationDTO.getExpiredDate()))) {
                throw new CustomException("인증기간이 만료 되었습니다. 재가입 해주세요.", HttpStatus.BAD_REQUEST);
            } else {
                if(!(userMailVerificationDTO.getToken().equals(p.getToken()))) {
                    throw new CustomException("인증번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
                }
            }
        }

        return userMapper.delUserEmailVerification(p.getUserId());
    }

    // 로그인
    @Transactional
    public UserSignInRes signIn(UserSignInReq p) {
        UserSignInRes res = userMapper.selUserByUid(p.getId());

        if(res == null || !BCrypt.checkpw(p.getPw(),res.getUpw())) {
            throw new CustomException("아이디 혹은 비밀번호를 확인해 주세요.", HttpStatus.BAD_REQUEST);
        } else {
            // 인증 여부 검사
            /*UserMailVerificationDTO userMailVerificationDTO = userMapper.selUserEmailVerificationByUserId(res.getUserId());
            LocalDateTime now = LocalDateTime.now();

            if(userMailVerificationDTO != null) {
                String msg = "인증이 완료되지 않았습니다.";

                if (now.isAfter(DateTimeUtils.convertToLocalDateTime(userMailVerificationDTO.getExpiredDate()))){
                    msg = "인증기간이 만료 되었습니다. 재가입 해주세요.";
                }

                throw new CustomException(msg, HttpStatus.BAD_REQUEST);
            }*/
        }

        return res;
    }

    // 아이디 찾기
    public int findId(UserFindIdReq p) {
        // 이메일이 존재하는지
        UserDTO userDTO = userMapper.selUserByEmailAndName(p);

        if(userDTO == null) {
            throw new CustomException("이메일이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        } else {
            // 인증번호 이메일 전송
            mailSendService.sendFindIdMail(p.getEmail(), userDTO.getUid());
        }

        return 1;
    }
}
