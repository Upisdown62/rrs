package com.jyujyu.review.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateAndEditRestaurantRequestMenu {
    private String name;
    private Integer price;
}
