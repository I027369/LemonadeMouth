package com.moonshot.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.MenuItem;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

	public List<MenuItem> findByRestaurantId(Long id);
	public List<MenuItem> findByMenuId(Long id);

}
