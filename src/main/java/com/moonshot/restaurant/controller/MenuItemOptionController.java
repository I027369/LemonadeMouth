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
import com.moonshot.restaurant.api.model.APIOrder;
import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.repository.MenuItemOptionRepository;
import com.moonshot.restaurant.service.AppUserService;
import com.moonshot.restaurant.service.MenuItemOptionService;
import com.moonshot.restaurant.service.RestaurantService;


@RestController
public class MenuItemOptionController {

	@Autowired
	private MenuItemOptionService menuItemOptionService;
	
	@RequestMapping(method=RequestMethod.GET, value="/menuitemchoices/{menuItemChoicesId}/menuitemoptions")
	public List<APIMenuItemOption> getMenuItemOptions(@PathVariable Long menuItemChoicesId) throws WriterException {
		return menuItemOptionService.getMenuItemOptions(menuItemChoicesId);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/menuitemoptions/{id}")
	public APIMenuItemOption getMenuItemOption(@PathVariable Long id)throws WriterException {
		return menuItemOptionService.getMenuItemOption(id);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/menuitemoptions")
	public APIMenuItemOption addMenuItemOption(@RequestBody APIMenuItemOption apiMenuItemOption) throws WriterException{
		return menuItemOptionService.addMenuItemOption(apiMenuItemOption);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/menuitemoptions/{id}")
	public APIMenuItemOption updateMenuItemOption(@RequestBody APIMenuItemOption apiMenuItemOption){
		return menuItemOptionService.updateMenuItemOption(apiMenuItemOption);		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/menuitemoptions/{id}")
	public void deleteMenuItemOption(@PathVariable Long id)throws WriterException {
		menuItemOptionService.deleteMenuItemOption(id);		
	}	

}
