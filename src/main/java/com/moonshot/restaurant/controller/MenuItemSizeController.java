package com.moonshot.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.api.model.APIMenuItemOption;
import com.moonshot.restaurant.api.model.APIMenuItemSize;
import com.moonshot.restaurant.api.model.APIOrder;
import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.repository.MenuItemOptionRepository;
import com.moonshot.restaurant.service.AppUserService;
import com.moonshot.restaurant.service.MenuItemOptionService;
import com.moonshot.restaurant.service.MenuItemSizeService;
import com.moonshot.restaurant.service.RestaurantService;


@RestController
public class MenuItemSizeController {

	@Autowired
	private MenuItemSizeService menuItemSizeService;
	
	@RequestMapping(method=RequestMethod.GET, value="/menuitems/{menuItemId}/menuitemsizes")
	public List<APIMenuItemSize> getMenuItemSizes(@PathVariable Long menuItemId) throws WriterException{
		return menuItemSizeService.getMenuItemSizes(menuItemId);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/menuitemsizes/{id}")
	public APIMenuItemSize getMenuItemSize(@PathVariable Long id) throws WriterException{
		return menuItemSizeService.getMenuItemSize(id);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/menuitemsizes")
	public APIMenuItemSize addMenuItemSize(@RequestBody APIMenuItemSize apiMenuItemSize) throws WriterException{
		return menuItemSizeService.addMenuItemSize(apiMenuItemSize);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/menuitemsizes/{id}")
	public APIMenuItemSize updateMenuItemSize(@RequestBody APIMenuItemSize apiMenuItemSize){
		return menuItemSizeService.updateMenuItemSize(apiMenuItemSize);		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/menuitemsizes/{id}")
	public void deleteMenuItemSize(@PathVariable Long id) throws WriterException{
		menuItemSizeService.deleteMenuItemSize(id);		
	}	

}
