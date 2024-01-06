package com.jyujyu.review.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@Table(name = "menu")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;
    private String name;
    private Integer price;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}