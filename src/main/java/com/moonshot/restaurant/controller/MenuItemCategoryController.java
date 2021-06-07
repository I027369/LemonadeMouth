package com.moonshot.restaurant.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.api.model.APIMenuItemCategory;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.MenuItemCategory;
import com.moonshot.restaurant.entity.RestaurantTable;
import com.moonshot.restaurant.service.MenuItemCategoryService;
import com.moonshot.restaurant.service.MenuItemService;
import com.moonshot.restaurant.service.RestaurantTableService;



@RestController
public class MenuItemCategoryController {

	@Autowired
	private MenuItemCategoryService menuItemCategoryService;
	

	@RequestMapping(method=RequestMethod.GET, value="/restaurants/{restaurantId}/menuitemcategories")
	public List<APIMenuItemCategory> getMenuItemCategories(@PathVariable Long restaurantId)throws WriterException{
		return menuItemCategoryService.getAllMenuItemCategories(restaurantId);
	}
	

	@RequestMapping(method=RequestMethod.GET, value="/menuitemcategories/{menuitemcategoryid}")
	public APIMenuItemCategory getMenuItemCategory(@PathVariable Long menuitemcategoryid)throws WriterException{
		return menuItemCategoryService.getMenuItemCategory(menuitemcategoryid);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/menuitemcategories")
	public APIMenuItemCategory addMenuItemCategory(@RequestBody APIMenuItemCategory apiMenuItemCategory)throws WriterException, IOException{	
		return menuItemCategoryService.addMenuItemCategory(apiMenuItemCategory);			
	}	
	
		
	@RequestMapping(method=RequestMethod.PUT, value="/menuitemcategories/{menuitemcategoryid}")
	public APIMenuItemCategory updateMenuItemCategory(@RequestBody APIMenuItemCategory apiMenuItemCategory, 
			@PathVariable Long menuitemcategoryid)throws WriterException, IOException{
		return menuItemCategoryService.updateMenuItemCategory(menuitemcategoryid, apiMenuItemCategory);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/menuitemcategories/{menuitemcategoryid}")
	public void deleteMenuItemCategory(@PathVariable Long menuitemcategoryid)throws WriterException{
		menuItemCategoryService.deleteMenuItemCategory(menuitemcategoryid);
	}
	
}
