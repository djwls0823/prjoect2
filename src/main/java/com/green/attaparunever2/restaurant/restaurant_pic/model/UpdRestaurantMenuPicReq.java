package com.green.attaparunever2.restaurant.restaurant_pic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class UpdRestaurantMenuPicReq {
    private long menuId;
    private String menuPic;
    private String picName;
}
