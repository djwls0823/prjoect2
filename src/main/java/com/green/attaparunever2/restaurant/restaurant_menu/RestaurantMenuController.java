package com.green.attaparunever2.restaurant.restaurant_menu;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.restaurant.restaurant_menu.model.InsMenuReq;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("restaurant/menu")
public class RestaurantMenuController {
    private final RestaurantMenuService restaurantMenuService;

    @PostMapping
    @Operation(summary = "메뉴 등록")
    public ResultResponse<Integer> postMenu(@RequestBody InsMenuReq p){
        int result = restaurantMenuService.postMenu(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("메뉴 등록 성공")
                .resultData(result)
                .build();
    }
}
