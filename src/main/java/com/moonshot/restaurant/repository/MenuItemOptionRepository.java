package com.moonshot.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.MenuItemOption;

public interface MenuItemOptionRepository extends CrudRepository<MenuItemOption, Long> {
	
	public List<MenuItemOption> findByMenuItemChoiceId(Long menuItemId);

}
