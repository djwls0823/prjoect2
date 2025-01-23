package com.green.attaparunever2.user;

import com.green.attaparunever2.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserSignUpReq req);
    UserSignInRes selUserByUid(String uId);
    UserGetRes selUserByUserId(long userId);
    UserDTO selUserByEmailAndName(UserFindIdReq req);
    int delUser(long userId);
    int insUserEmailVerification(UserMailVerificationDTO dto);
    UserMailVerificationDTO selUserEmailVerificationByUserId(long userId);
    UserMailVerificationDTO selUserEmailVerificationByUId(String uId);
    int delUserEmailVerification(long userId);

}
