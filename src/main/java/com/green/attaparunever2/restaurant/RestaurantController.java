package com.green.attaparunever2.restaurant;


import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.restaurant.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("restaurant")
@Tag(name = "식당", description = "식당 관리")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    @Operation(summary = "식당 등록")
    public ResultResponse<InsRestaurantRes> postRestaurant(@RequestPart List<MultipartFile> filePath, @RequestPart InsRestaurantReq p){
        InsRestaurantRes result = restaurantService.postRestaurant(filePath, p);

        return ResultResponse.<InsRestaurantRes>builder()
                .statusCode("200")
                .resultMsg("식당 등록 성공")
                .resultData(result)
                .build();
    }

    @GetMapping
    @Operation(summary = "식당 상세 정보 보기")
    public ResultResponse<SelRestaurantRes> getRestaurant(@ParameterObject @ModelAttribute SelRestaurantReq p){
        SelRestaurantRes res = restaurantService.getRestaurant(p);

        return ResultResponse.<SelRestaurantRes>builder()
                .statusCode("200")
                .resultMsg("식당 보기 성공")
                .resultData(res)
                .build();
    }

    @GetMapping("around")
    @Operation(summary = "주변 식당 보기")
    public ResultResponse<List<SelRestaurantAroundRes>> getRestaurantAround(@ParameterObject @ModelAttribute SelRestaurantAroundReq p){
        List<SelRestaurantAroundRes> res = restaurantService.getRestaurantAround(p);

        return ResultResponse.<List<SelRestaurantAroundRes>>builder()
                .statusCode("200")
                .resultMsg("주변 식당 보기 완료")
                .resultData(res)
                .build();
    }

    @PostMapping("holiday")
    @Operation(summary = "휴무일 등록")
    public ResultResponse<Integer> postHoliday(@RequestBody InsHolidayReq p){
        int result = restaurantService.postHoliday(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("휴무일 등록 성공")
                .resultData(result)
                .build();
    }

    @GetMapping("holiday")
    @Operation(summary = "휴무일 보기")
    public ResultResponse<List<SelHolidayRes>> getHoliday(@ParameterObject @ModelAttribute SelHolidayReq p){
        List<SelHolidayRes> res = restaurantService.getHoliday(p);

        return ResultResponse.<List<SelHolidayRes>>builder()
                .statusCode("200")
                .resultMsg("휴무일 보기 완료")
                .resultData(res)
                .build();
    }

    @PatchMapping
    @Operation(summary = "식당 정보 수정")
    public ResultResponse<Integer> updateRestaurant(@RequestBody UpdRestaurantReq req){
        int result = restaurantService.patchRestaurant(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("식당 정보 수정 완료")
                .resultData(result)
                .build();
    }

    @PatchMapping("holiday")
    @Operation(summary = "휴무일 변경")
    public ResultResponse<Integer> updateHoliday(@RequestBody UpdHolidayReq req){
        int result = restaurantService.patchHoliday(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("휴무일 변경 완료")
                .resultData(result)
                .build();
    }

    @GetMapping("main")
    @Operation(summary = "메인 화면 식당 조회")
    public ResultResponse<List<SelRestaurantMainRes>> getRestaurantMain(@ParameterObject @ModelAttribute SelRestaurantMainReq p){
        List<SelRestaurantMainRes> res = restaurantService.getRestaurantMain(p);

        return ResultResponse.<List<SelRestaurantMainRes>>builder()
                .statusCode("200")
                .resultMsg("메인 화면 식당 정보 조회 완료")
                .resultData(res)
                .build();
    }
}