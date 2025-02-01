package com.green.attaparunever2.restaurant.restaurant_pic;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.restaurant.model.InsRestaurantRes;
import com.green.attaparunever2.restaurant.restaurant_pic.model.UpdRestaurantMenuPicReq;
import com.green.attaparunever2.restaurant.restaurant_pic.model.UpdRestaurantPicReq;
import io.swagger.v3.oas.annotations.Operation;
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

    @PatchMapping("/restaurant")
    @Operation(summary = "식당 사진 수정")
    public ResultResponse<List<String>> updRestaurantPic(@RequestPart List<MultipartFile> pics, @RequestPart UpdRestaurantPicReq p) {
        List<String> result = restaurantPicService.updRestaurantPic(pics, p);

        return ResultResponse.<List<String>>builder()
                .statusCode("200")
                .resultMsg("식당 사진 수정 성공")
                .resultData(result)
                .build();
    }
}
