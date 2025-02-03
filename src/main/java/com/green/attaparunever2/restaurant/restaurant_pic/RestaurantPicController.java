package com.green.attaparunever2.restaurant.restaurant_pic;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.restaurant.restaurant_pic.model.UpdRestaurantMenuPicReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("pic")
@Tag(name = "사진 수정", description = "식당과 메뉴 사진 수정")
public class RestaurantPicController {
    private final RestaurantPicService restaurantPicService;

    @PatchMapping("/restaurant/menu")
    @Operation(summary = "식당 메뉴 사진 수정")
    public ResultResponse<String> updRestaurantMenuPic(@RequestPart MultipartFile pic, @RequestPart UpdRestaurantMenuPicReq p) {
        String result = restaurantPicService.updRestaurantMenuPic(pic, p);

        return ResultResponse.<String>builder()
                .statusCode("200")
                .resultMsg("식당 메뉴 사진 수정 성공")
                .resultData(result)
                .build();
    }

    @DeleteMapping("/restaurant")
    @Operation(summary = "식당 사진 삭제")
    public ResultResponse<Integer> delRestaurantPic(
            @Parameter(description = "식당 사진 ID", example = "[1, 2, 3]", required = true)
            @RequestParam List<Long> picId,
            @RequestParam long restaurantId) {
        // service 호출 및 응답 처리
        int result = restaurantPicService.delRestaurantPic(restaurantId, picId);
        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("식당 사진 삭제 성공")
                .resultData(result)
                .build();
    }
}
