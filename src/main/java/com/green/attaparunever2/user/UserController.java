package com.green.attaparunever2.user;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.user.model.AuthTokenReq;
import com.green.attaparunever2.user.model.UserSignInReq;
import com.green.attaparunever2.user.model.UserSignInRes;
import com.green.attaparunever2.user.model.UserSignUpReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name="유저", description = "유저 관련 API")
public class UserController {
    private final UserService userService;

    @PostMapping("sign-up")
    @Operation(summary = "회원가입")
    public ResultResponse<?> signUp(@RequestBody UserSignUpReq req) {
        int result = userService.signUp(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("회원가입에 성공하였습니다.")
                .resultData(result)
                .build();
    }

    @GetMapping("auth-token")
    @Operation(summary = "인증번호 인증")
    public ResultResponse<?> authToken(@ModelAttribute AuthTokenReq req) {
        int result = userService.authToken(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("메일 인증에 성공했습니다.")
                .resultData(result)
                .build();
    }

    @PostMapping("sign-in")
    @Operation(summary = "로그인")
    public ResultResponse<?> signIn(@RequestBody UserSignInReq p) {
        UserSignInRes userSignInRes = userService.signIn(p);

        return ResultResponse.<UserSignInRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("로그인 성공")
                .resultData(userSignInRes)
                .build();
    }
}
