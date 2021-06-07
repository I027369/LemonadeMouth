package com.moonshot.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.RestaurantAddress;
import com.moonshot.restaurant.entity.RestaurantTable;
import com.moonshot.restaurant.service.MenuItemService;
import com.moonshot.restaurant.service.RestaurantAddressService;



@RestController
public class ResturantAddressController {
/*
	@Autowired
	private RestaurantAddressService restaurantService;
	
	@RequestMapping(method=RequestMethod.GET, value="/restaurants/{restaurantId}/restaurantaddress")
	public List<RestaurantAddress> getAllRestaurantAddress(@PathVariable Long restaurantId){
		return restaurantService.getAllRestaurantAddress(restaurantId);
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:8081")
	@RequestMapping(method=RequestMethod.GET, value="/restaurants/{restaurantId}/restaurantaddress/{restaurantaddressid}")
	public RestaurantAddress getRestaurantAddress(@PathVariable Long restaurantaddressid){
		return restaurantService.getRestaurantAddress(restaurantaddressid);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/restaurants/{restaurantId}/restaurantaddress/{restaurantaddressid}")
	public void deleteRestaurantAddress(@PathVariable Long restaurantaddressid){
		restaurantService.deleteRestaurantAddress(restaurantaddressid);
	}
*/
}
