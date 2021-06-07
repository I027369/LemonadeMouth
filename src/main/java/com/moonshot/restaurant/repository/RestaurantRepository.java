package com.moonshot.restaurant.repository;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

}
