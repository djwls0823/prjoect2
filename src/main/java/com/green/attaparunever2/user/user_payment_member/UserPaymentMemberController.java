package com.green.attaparunever2.user.user_payment_member;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.user.UserService;
import com.green.attaparunever2.user.model.*;
import com.green.attaparunever2.user.user_payment_member.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/user-payment-member")
@Tag(name="유저 결제 및 포인트 관련", description = "유저 결제 및 포인트 관련 API")
public class UserPaymentMemberController {
    private final UserPaymentMemberService userPaymentMemberService;

    @GetMapping("getPoint")
    @Operation(summary = "남은 포인트 조회")
    public ResultResponse<UserGetPointRes> getPoint(@ModelAttribute UserGetPointReq p) {
        UserGetPointRes result = userPaymentMemberService.getPoint(p.getUserId());

        return ResultResponse.<UserGetPointRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("포인트 조회 완료.")
                .resultData(result)
                .build();
    }

    @PostMapping("postPayment")
    @Operation(summary = "결제 등록")
    public ResultResponse<Integer> postPayment(@ModelAttribute UserPostPaymentReq p) {
        int result = userPaymentMemberService.postPayment(p);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("결제 등록 완료.")
                .resultData(result)
                .build();
    }

    @GetMapping("getPaymentMember")
    @Operation(summary = "결제에 해당하는 인원을 조회한다.")
    public ResultResponse<Integer> getPaymentMember(@ModelAttribute UserGetPaymentMemberReq p) {
        int result = userPaymentMemberService.getPaymentMember(p.getOrderId());

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("포인트 조회 완료.")
                .resultData(result)
                .build();
    }

    @GetMapping("getPaymentInfo")
    @Operation(summary = "내게 온 결제 승인 요청 정보를 조회한다.")
    public ResultResponse<UserGetPaymentInfoRes> getPaymentInfo(@ModelAttribute UserGetPaymentInfoReq p) {
        UserGetPaymentInfoRes result = userPaymentMemberService.getPaymentInfo(p);

        return ResultResponse.<UserGetPaymentInfoRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("내게 온 결제 승인요청 정보조회 완료.")
                .resultData(result)
                .build();
    }

    @PatchMapping
    @Operation(summary = "내게 온 결제 승인 요청 처리", description = "승인 및 거부 처리")
    public ResultResponse<Integer> patchPaymentMember(@RequestBody UserPatchPaymentMemberReq p) {
        int result = userPaymentMemberService.patchPaymentMember(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("내게 온 결제 승인 요청 수정 완료")
                .resultData(result)
                .build();
    }

    @PostMapping
    @Operation(summary = "결제 승인 요청")
    public ResultResponse<Integer> postPaymentMember(@RequestBody UserPostPaymentMemberReq p) {
        int result = userPaymentMemberService.postPaymentMember(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("승인 요청 보내기 성공")
                .resultData(result)
                .build();
    }
}
