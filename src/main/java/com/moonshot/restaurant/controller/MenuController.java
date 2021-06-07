package com.moonshot.restaurant.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.api.model.APIMenu;
import com.moonshot.restaurant.api.model.APIMenuDetails;
import com.moonshot.restaurant.api.model.APIMenuItem;
import com.moonshot.restaurant.api.model.APIMenuWithItemAndCategory;
import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.RestaurantTable;
import com.moonshot.restaurant.service.MenuItemService;
import com.moonshot.restaurant.service.MenuService;
import com.moonshot.restaurant.service.RestaurantTableService;

import io.swagger.annotations.ApiParam;


@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	/*public List<APIMenu> getMenus( @PathVariable Long restaurantId,  
			@ApiParam(value = "Example text \"?time=10:10\"", required = false) @RequestParam Map<String, String> queryParameters
			) throws WriterException */
	
	@RequestMapping(method=RequestMethod.GET, value="/restaurants/{restaurantId}/menus")
	public List<APIMenu> getAllMenus( @PathVariable Long restaurantId) throws WriterException{
		return menuService.getAllMenu(restaurantId);
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/menus/{menuid}")
	public APIMenuDetails getMenu(@PathVariable Long menuid) throws WriterException{
		return menuService.getMenu(menuid);
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST, value="/menus")
	public APIMenu addMenu(@RequestBody APIMenu apiMenu)throws WriterException, IOException{	
		return menuService.addMenu(apiMenu);			
	}	

		
	@RequestMapping(method=RequestMethod.PUT, value="/menus/{menuid}")
	public APIMenu updateMenu(
			@RequestBody (required=false) APIMenu apiMenu, 
			@PathVariable Long menuid,
			@ApiParam(value = "Example text \"?status=Pulished\"", required = false) @RequestParam Map<String, String> queryParameters			
			)throws WriterException, IOException{		
		String localStatus = queryParameters.get("status");
		if(localStatus != null){				
			return menuService.updateMenuStatus(menuid,localStatus);
		}
		else
			return menuService.updateMenu(menuid, apiMenu);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/menus/{menuid}")
	public void deleteMenu(@PathVariable Long menuid) throws WriterException{
		menuService.deleteMenu(menuid);
	}
	/*
	@RequestMapping(method=RequestMethod.POST, value="/menus/{menuid}/menuitems")
	public APIMenu createMenuItemToMenu(@PathVariable Long menuid, @RequestBody APIMenuItem apiMenuItem)throws WriterException, IOException{	
		return menuService.createMenuItemToMenu(menuid, apiMenuItem);			
	}	
	*/
	@RequestMapping(method=RequestMethod.POST, value="/menus/{menuid}/menuitems/{menuitemsid}")
	public APIMenuDetails addMenuItemToMenu(@PathVariable Long menuid, @PathVariable Long menuitemsid)throws WriterException, IOException{	
		return menuService.addMenuItemToMenu(menuid, menuitemsid);			
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/menus/{menuid}/menuitems/{menuitemsid}")
	public APIMenuDetails deleteMenuItemFromMenu(@PathVariable Long menuid, @PathVariable Long menuitemsid) throws WriterException{
		return menuService.delMenuItemFromMenu(menuid, menuitemsid);
	}
	
	
		
}
