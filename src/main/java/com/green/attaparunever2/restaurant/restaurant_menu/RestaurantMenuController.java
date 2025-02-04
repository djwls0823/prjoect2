package com.green.attaparunever2.restaurant.restaurant_menu;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.restaurant.restaurant_menu.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("restaurant/menu")
@Tag(name = "식당 메뉴", description = "식당 메뉴 관리")
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

//    @GetMapping
//    @Operation(summary = "카테고리 PK로 메뉴 보기")
//    public ResultResponse<List<SelMenuRes>> getMenu(@ParameterObject @ModelAttribute SelMenuReq p){
//        List<SelMenuRes> res = restaurantMenuService.getMenu(p);
//
//        return ResultResponse.<List<SelMenuRes>>builder()
//                .statusCode("200")
//                .resultMsg("메뉴 보기 완료")
//                .resultData(res)
//                .build();
//    }

    @DeleteMapping
    public ResultResponse<Integer> deleteMenu(@ParameterObject @ModelAttribute DelMenuReq p){
        int result = restaurantMenuService.delMenu(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("메뉴 삭제 완료")
                .resultData(result)
                .build();
    }

    @PatchMapping
    @Operation(summary = "메뉴 정보 수정")
    public ResultResponse<Integer> updateMenu(@RequestBody UpdMenuReq p){
        int result = restaurantMenuService.updRestaurantMenu(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("메뉴 수정 완료")
                .resultData(result)
                .build();
    }

    @PostMapping("/category")
    @Operation(summary = "메뉴 카테고리 등록")
    public ResultResponse<Integer> postCategory(@RequestBody PostCategoryReq p) {
        int result = restaurantMenuService.postCategory(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("메뉴 카테고리 등록 완료")
                .resultData(result)
                .build();
    }

    @PatchMapping("/category")
    @Operation(summary = "메뉴 카테고리 수정")
    public ResultResponse<Integer> updateCategory(@RequestBody UpdCategoryReq p) {
        int result = restaurantMenuService.updCategory(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("메뉴 카테고리 수정 완료")
                .resultData(result)
                .build();
    }

    @DeleteMapping("/category")
    @Operation(summary = "메뉴 카테고리 삭제")
    public ResultResponse<Integer> deleteCategory(@RequestBody DelCategoryReq p) {
        int result = restaurantMenuService.delCategory(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("메뉴 카테고리 삭제 완료")
                .resultData(result)
                .build();
    }
}
