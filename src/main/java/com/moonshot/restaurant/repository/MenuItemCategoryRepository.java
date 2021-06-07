package com.moonshot.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.MenuItemCategory;


public interface MenuItemCategoryRepository extends CrudRepository<MenuItemCategory, Long> {
	
	public List<MenuItemCategory> findByRestaurantId(Long id);

}
