package com.green.attaparunever2.restaurant;


import com.green.attaparunever2.restaurant.model.InsHolidayReq;
import com.green.attaparunever2.restaurant.model.InsRestaurantReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantMapper restaurantMapper;

    public int postRestaurant(InsRestaurantReq p){
        int result = restaurantMapper.insRestaurant(p);

        return result;
    }

    public int postHoliday(InsHolidayReq p){
        int result = restaurantMapper.insHoliday(p);

        return result;
    }

}
