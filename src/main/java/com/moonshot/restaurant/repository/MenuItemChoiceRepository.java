package com.moonshot.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.MenuItemChoice;
import com.moonshot.restaurant.entity.MenuItemOption;

public interface MenuItemChoiceRepository extends CrudRepository<MenuItemChoice, Long> {
	
	public List<MenuItemChoice> findByMenuItemId(Long menuItemId);

}
