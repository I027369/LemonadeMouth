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
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.RestaurantTable;
import com.moonshot.restaurant.service.MenuItemService;
import com.moonshot.restaurant.service.RestaurantTableService;



@RestController
public class RestaurantTableController {

	@Autowired
	private RestaurantTableService restaurantTableService;
	

	@RequestMapping(method=RequestMethod.GET, value="/restaurants/{restaurantId}/tables")
	public List<RestaurantTable> getRestaurantTables(@PathVariable Long restaurantId){
		return restaurantTableService.getAllRestaurantTables(restaurantId);
	}
	

	@RequestMapping(method=RequestMethod.GET, value="/restaurants/{restaurantId}/tables/{tableid}")
	public RestaurantTable getRestaurantTable(@PathVariable Long tableid){
		return restaurantTableService.getRestaurantTable(tableid);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/restaurants/{restaurantId}/tables")
	public RestaurantTable addRestaurantTable(@RequestBody RestaurantTable restaurantTale, @PathVariable Long restaurantId)throws WriterException, IOException{	
		return restaurantTableService.addRestaurantTable(restaurantId, restaurantTale);			
	}	
	
		
	@RequestMapping(method=RequestMethod.PUT, value="/restaurants/{restaurantId}/tables/{tableid}")
	public RestaurantTable updateRestaurantTable(@RequestBody RestaurantTable restaurantTable, @PathVariable Long tableid)throws WriterException, IOException{
		return restaurantTableService.updateRestaurantTable(tableid, restaurantTable);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/restaurants/{restaurantId}/tables/{tableid}")
	public void deleteRestaurantTable(@PathVariable Long tableid){
		restaurantTableService.deleteRestaurantTable(tableid);
	}
	
}
