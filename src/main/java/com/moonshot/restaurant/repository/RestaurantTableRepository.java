package com.moonshot.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.RestaurantTable;

public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Long> {

	public List<RestaurantTable> findByRestaurantId(Long id);	
}
