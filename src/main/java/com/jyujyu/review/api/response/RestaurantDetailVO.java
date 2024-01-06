package com.jyujyu.review.api.response;


import com.jyujyu.review.model.MenuEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class RestaurantDetailVO extends RestaurantVO {
    private List<MenuEntity> menus;
}
