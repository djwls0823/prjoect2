package com.green.attaparunever2.restaurant.restaurant_menu;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.restaurant.restaurant_menu.model.DelMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.InsMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.SelMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.SelMenuRes;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("restaurant/menu")
public class RestaurantMenuController {
    private final RestaurantMenuService restaurantMenuService;

    @PostMapping
    @Operation(summary = "메뉴 등록")
    public ResultResponse<Integer> postMenu(@RequestPart InsMenuReq p
                                            , @RequestPart(required = false) MultipartFile pic){

        int result = restaurantMenuService.postMenu(pic, p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("메뉴 등록 성공")
                .resultData(result)
                .build();
    }

    @GetMapping
    @Operation(summary = "카테고리 PK로 메뉴 보기")
    public ResultResponse<List<SelMenuRes>> getMenu(@ParameterObject @ModelAttribute SelMenuReq p){
        List<SelMenuRes> res = restaurantMenuService.getMenu(p);

        return ResultResponse.<List<SelMenuRes>>builder()
                .statusCode("200")
                .resultMsg("메뉴 보기 완료")
                .resultData(res)
                .build();
    }

    @DeleteMapping
    public ResultResponse<Integer> deleteMenu(@ParameterObject @ModelAttribute DelMenuReq p){
        int result = restaurantMenuService.delMenu(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("메뉴 삭제 완료")
                .resultData(result)
                .build();
    }

}
