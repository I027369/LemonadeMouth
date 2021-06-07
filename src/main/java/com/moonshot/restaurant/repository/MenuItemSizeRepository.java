package com.moonshot.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.MenuItemOption;
import com.moonshot.restaurant.entity.MenuItemSize;

public interface MenuItemSizeRepository extends CrudRepository<MenuItemSize, Long> {
	
	public List<MenuItemSize> findByMenuItemId(Long menuItemId);

}
