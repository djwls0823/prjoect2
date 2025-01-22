package com.green.attaparunever2.restaurant.restaurant_menu;


import com.green.attaparunever2.restaurant.restaurant_menu.model.InsMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.SelMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.SelMenuRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantMenuService {
    private final RestaurantMenuMapper restaurantMenuMapper;

    public int postMenu(InsMenuReq p){
        int result = restaurantMenuMapper.insMenu(p);
        return result;
    }

    public List<SelMenuRes> getMenu(SelMenuReq p){
        List<SelMenuRes> res = restaurantMenuMapper.selMenu(p);

        return res;
    }
}
