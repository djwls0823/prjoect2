package com.green.attaparunever2.admin;

import com.green.attaparunever2.admin.model.AdminSignUpReq;
import com.green.attaparunever2.common.DateTimeUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.admin.model.*;
import com.green.attaparunever2.user.MailSendService;
import com.green.attaparunever2.admin.model.AdminMailVerificationDTO;
import com.green.attaparunever2.user.MailSendService;
import com.green.attaparunever2.user.model.UserGetReq;
import com.green.attaparunever2.user.model.UserGetRes;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;
    private final MailSendService mailSendService;

    // 관리자 회원가입
    @Transactional
    public int adminSignUp(AdminSignUpReq req){
        // 인증정보 조회(만약 인증이 만료된 아이디로 가입하려는 경우 인증 정보를 지우고 유저 정보도 지워야 함)
        /*AdminMailVerificationDTO adminMailVerificationDTO = adminMapper.selAdminEmailVerificationByAId(req.getAid());

        if(adminMailVerificationDTO != null) {
            LocalDateTime now = LocalDateTime.now();

            // 인증 기간이 만료되었다면 인증, 회원정보 삭제
            if (now.isAfter(DateTimeUtils.convertToLocalDateTime(adminMailVerificationDTO.getExpiredDate()))) {
                adminMapper.delAdminEmailVerification(adminMailVerificationDTO.getAdminId());
                adminMapper.delAdmin(adminMailVerificationDTO.getAdminId());
            }
        }*/

        // 비밀번호 암호화
        req.setApw(BCrypt.hashpw(req.getApw(), BCrypt.gensalt()));
        
        int result = adminMapper.insAdmin(req);

        /*if(result != 0) {
            // 인증번호 생성
            String authKey = mailSendService.generateAuthCode(10);

            // 인증번호 저장
            adminMailVerificationDTO = new AdminMailVerificationDTO();
            adminMailVerificationDTO.setAdminId(req.getAdminId());
            adminMailVerificationDTO.setToken(authKey);

            result = adminMapper.insAdminEmailVerification(adminMailVerificationDTO);

            if(result == 0) {
                throw new CustomException("관리자 가입에 실패 했습니다.", HttpStatus.BAD_REQUEST);
            }

            // 인증번호 이메일 전송
            mailSendService.sendAuthMail("/admin/auth-token?adminId=", req.getEmail(), req.getAdminId(), adminMailVerificationDTO.getToken());
        }*/
        
        return result;
    }

    // 관리자 정보 조회
    public AdminGetRes getAdmin(AdminGetReq p) {
        AdminGetRes res = adminMapper.selAdminByAdminId(p.getAdminId());

        if(res == null) {
            throw new CustomException("관리자 정보를 불러올 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        return res;
    }

    // 관리자삭제
    @Transactional
    public int delAdmin(AdminDelReq p) {
        int result = adminMapper.delAdmin(p.getAdminId());

        if(result == 0) {
            throw new CustomException("관리자 삭제에 실패 했습니다.", HttpStatus.BAD_REQUEST);
        }

        return result;
    }

    // 인증처리
    @Transactional
    public int authToken(AuthTokenReq p) {
        AdminMailVerificationDTO adminMailVerificationDTO = adminMapper.selAdminEmailVerificationByAdminId(p.getAdminId());

        if(adminMailVerificationDTO == null) {
            throw new CustomException("인증 정보가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        } else {
            LocalDateTime now = LocalDateTime.now();

            if (now.isAfter(DateTimeUtils.convertToLocalDateTime(adminMailVerificationDTO.getExpiredDate()))) {
                throw new CustomException("인증기간이 만료 되었습니다. 재가입 해주세요.", HttpStatus.BAD_REQUEST);
            } else {
                if(!(adminMailVerificationDTO.getToken().equals(p.getToken()))) {
                    throw new CustomException("인증번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
                }
            }
        }

        return adminMapper.delAdminEmailVerification(p.getAdminId());
    }

    // 로그인
    @Transactional
    public AdminSignInRes signIn(AdminSignInReq p) {
        AdminSignInRes res = adminMapper.selAdminByAid(p.getId());

        if(res == null || !BCrypt.checkpw(p.getPw(),res.getApw())) {
            throw new CustomException("아이디 혹은 비밀번호를 확인해 주세요.", HttpStatus.BAD_REQUEST);
        } else {
            /*// 인증 여부 검사
            AdminMailVerificationDTO adminMailVerificationDTO = adminMapper.selAdminEmailVerificationByAdminId(res.getAdminId());
            LocalDateTime now = LocalDateTime.now();

            if(adminMailVerificationDTO != null) {
                String msg = "인증이 완료되지 않았습니다.";

                if (now.isAfter(DateTimeUtils.convertToLocalDateTime(adminMailVerificationDTO.getExpiredDate()))){
                    msg = "인증기간이 만료 되었습니다. 재가입 해주세요.";
                }

                throw new CustomException(msg, HttpStatus.BAD_REQUEST);
            }*/
        }

        return res;
    }

    // 아이디 찾기
    public int findId(AdminFindIdReq p) {
        // 이메일이 존재하는지
        AdminDTO adminDTO = adminMapper.selAdminByEmailAndName(p);

        if(adminDTO == null) {
            throw new CustomException("이메일이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        } else {
            // 인증번호 이메일 전송
            mailSendService.sendFindIdMail(p.getEmail(), adminDTO.getAid());
        }

        return 1;
    }
}
