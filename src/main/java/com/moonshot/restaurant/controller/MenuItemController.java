package com.moonshot.restaurant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.api.model.APIMenuItem;
import com.moonshot.restaurant.api.model.APIMenuItemDetails;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.service.MenuItemService;

import io.swagger.annotations.ApiParam;



@RestController
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemService;
	
	@RequestMapping(method=RequestMethod.GET, value="/menus/{menuId}/menuitems/all")
	public List<APIMenuItem> getAllMenuItems(@PathVariable Long menuId) throws WriterException{
		return menuItemService.getAllMenuItems(menuId);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/menus/{menuId}/menuitems")
	public List<APIMenuItem> getActiveMenuItems(@PathVariable Long menuId) throws WriterException{
		return menuItemService.getActiveMenuItems(menuId);
	}
	
	/*
	@RequestMapping(method=RequestMethod.GET, value="/restaurants/{restaurantId}/menuitems")
	public List<MenuItem> getMenuItems(@PathVariable Long restaurantId){
		return menuItemService.getAllMenuItems(restaurantId);
	}
	*/
	
	@RequestMapping(method=RequestMethod.GET, value="/menuitems/{menuItemId}")
	public APIMenuItemDetails getMenuItem(@PathVariable Long menuItemId) throws WriterException{
		return menuItemService.getMenuItem(menuItemId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/menuitems")
	public APIMenuItem addMenuItem(@RequestBody APIMenuItem apiMenuItem) throws WriterException{	
		return menuItemService.addMenuItem(apiMenuItem);			
	}	
	
		
	@RequestMapping(method=RequestMethod.PUT, value="/menuitems/{menuItemId}")
	public APIMenuItem updateMenuItem(
			@RequestBody (required=false) APIMenuItem apiMenuItem, 
			@PathVariable Long menuItemId,
			@ApiParam(value = "Example text \"?status=Pulished\"", required = false) @RequestParam Map<String, String> queryParameters
			) throws WriterException{
			String localStatus = queryParameters.get("status");
			if(localStatus != null){				
				return menuItemService.updateMenuItemStatus(menuItemId,localStatus);
			}
			else
				return menuItemService.updateMenuItem(menuItemId, apiMenuItem);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/menuitems/{menuItemId}")
	public void deleteMenuItem(@PathVariable Long menuItemId) throws WriterException {
		menuItemService.deleteMenuItem(menuItemId);
	}
/*	
	@RequestMapping(method=RequestMethod.PUT, value="/restaurants/{restaurantId}/addmenutomenuitem{menuId}/{menuItemId}")
	public void addMenuToMenuItem(@PathVariable Long menuItemId, @PathVariable Long menuId){	
		menuItemService.addMenuToMenuItem(menuId, menuItemId);			
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/restaurants/{restaurantId}/delmenufrommenuitem{menuId}/{menuItemId}")
	public void delMenuFromMenuItem(@PathVariable Long menuItemId, @PathVariable Long menuId){	
		menuItemService.delMenuFromMenuItem(menuId, menuItemId);			
	}
*/	
			
}
