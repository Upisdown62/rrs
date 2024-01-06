package com.jyujyu.review.api;

import com.jyujyu.review.api.request.CreateAndEditRestaurantRequest;
import com.jyujyu.review.api.response.RestaurantDetailVO;
import com.jyujyu.review.api.response.RestaurantVO;
import com.jyujyu.review.model.RestaurantEntity;
import com.jyujyu.review.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<RestaurantVO> getRestaurants(){
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public RestaurantDetailVO getRestaurantDetail(
            @PathVariable(name = "restaurantId") Long restaurantId
    ){
        return restaurantService.getRestaurantDetail(restaurantId);
    }

    @PostMapping("/restaurant")
    public void createRestaurant(
            @RequestBody CreateAndEditRestaurantRequest request
    ){
        restaurantService.createRestaurant(request);
    }

    @PutMapping("/restaurant/{restaurantId}")
    public void updateRestaurant(
            @PathVariable(name = "restaurantId") Long restaurantId,
            @RequestBody CreateAndEditRestaurantRequest request
    ){
        restaurantService.updateRestaurant(restaurantId, request);
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public void deleteRestaurant(
            @PathVariable(name = "restaurantId") Long restaurantId
    ){
        restaurantService.deleteRestaurant(restaurantId);
    }
}
