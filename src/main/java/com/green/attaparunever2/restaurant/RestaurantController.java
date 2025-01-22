package com.green.attaparunever2.restaurant;


import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.restaurant.model.InsRestaurantReq;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    @Operation(summary = "식당 등록")
    public ResultResponse<Integer> postRestaurant(@RequestBody InsRestaurantReq p){
        int result = restaurantService.postRestaurant(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("식당 등록 성공")
                .resultData(result)
                .build();
    }
}
