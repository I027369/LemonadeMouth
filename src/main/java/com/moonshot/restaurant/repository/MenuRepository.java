package com.moonshot.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.RestaurantTable;

public interface MenuRepository extends CrudRepository<Menu, Long> {

	public List<Menu> findByRestaurantId(Long id);	

}
