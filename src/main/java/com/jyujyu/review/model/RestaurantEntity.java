package com.jyujyu.review.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@Table(name = "restaurant")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public void changeRestaurant(String name, String address){
        this.name = name;
        this.address = address;
        this.updatedAt = ZonedDateTime.now();
    }
}