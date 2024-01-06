package com.jyujyu.review.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateAndEditRestaurantRequest {

    private String name;
    private String address;
    private List<CreateAndEditRestaurantRequestMenu> menus;
}
