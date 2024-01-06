package com.jyujyu.review.service;

import com.jyujyu.review.api.request.CreateAndEditRestaurantRequest;
import com.jyujyu.review.model.MenuEntity;
import com.jyujyu.review.model.RestaurantEntity;
import com.jyujyu.review.repository.MenuRepository;
import com.jyujyu.review.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
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
