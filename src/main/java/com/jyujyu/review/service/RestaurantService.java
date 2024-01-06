package com.jyujyu.review.service;

import com.jyujyu.review.api.request.CreateAndEditRestaurantRequest;
import com.jyujyu.review.api.response.RestaurantDetailVO;
import com.jyujyu.review.api.response.RestaurantVO;
import com.jyujyu.review.model.MenuEntity;
import com.jyujyu.review.model.RestaurantEntity;
import com.jyujyu.review.repository.MenuRepository;
import com.jyujyu.review.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public List<RestaurantVO> getRestaurants(){
        List<RestaurantEntity> restaurantEntities
                = restaurantRepository.findAll();

        return restaurantEntities.stream().map((item) ->
                RestaurantVO
                        .builder()
                        .id(item.getId())
                        .name(item.getName())
                        .address(item.getAddress())
                        .createdAt(item.getCreatedAt())
                        .updatedAt(item.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RestaurantDetailVO getRestaurantDetail(Long restaurantId){
        RestaurantEntity restaurantEntity =
                restaurantRepository.findById(restaurantId).orElseThrow(
                        () -> new RuntimeException("없는 레스토랑입니다.")
                );

        List<MenuEntity> menuEntities
            = menuRepository.findAllByRestaurantId(restaurantId);

        return RestaurantDetailVO
                .builder()
                .id(restaurantId)
                .name(restaurantEntity.getName())
                .address(restaurantEntity.getAddress())
                .createdAt(restaurantEntity.getCreatedAt())
                .updatedAt(restaurantEntity.getUpdatedAt())
                .menus(menuEntities)
                .build();
    }

    @Transactional
    public RestaurantEntity createRestaurant(
            CreateAndEditRestaurantRequest request
    ){
        RestaurantEntity restaurantEntity =
                RestaurantEntity
                        .builder()
                        .name(request.getName())
                        .address(request.getAddress())
                        .createdAt(ZonedDateTime.now())
                        .updatedAt(ZonedDateTime.now())
                        .build();

        restaurantRepository.save(restaurantEntity);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity =
                    MenuEntity
                            .builder()
                            .restaurantId(restaurantEntity.getId())
                            .name(menu.getName())
                            .price(menu.getPrice())
                            .createdAt(ZonedDateTime.now())
                            .updatedAt(ZonedDateTime.now())
                            .build();

            menuRepository.save(menuEntity);
        });

        return restaurantEntity;
    }

    @Transactional
    public void updateRestaurant(
            Long restaurantId,
            CreateAndEditRestaurantRequest request
    ){
        RestaurantEntity restaurantEntity =
                restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RuntimeException("없는 레스토랑입니다.")
        );

        restaurantEntity.changeRestaurant(request.getName(), request.getAddress());
        restaurantRepository.save(restaurantEntity);

        List<MenuEntity> menuEntities
                = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menuEntities);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity =
                    MenuEntity
                            .builder()
                            .restaurantId(restaurantId)
                            .name(menu.getName())
                            .price(menu.getPrice())
                            .createdAt(ZonedDateTime.now())
                            .updatedAt(ZonedDateTime.now())
                            .build();

            menuRepository.save(menuEntity);
        });
    }

    @Transactional
    public void deleteRestaurant(long restaurantId){
        RestaurantEntity restaurantEntity =
                restaurantRepository.findById(restaurantId).orElseThrow(
                        () -> new RuntimeException("없는 레스토랑입니다.")
                );

        restaurantRepository.delete(restaurantEntity);

        List<MenuEntity> menuEntities
                = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menuEntities);
    }
}
