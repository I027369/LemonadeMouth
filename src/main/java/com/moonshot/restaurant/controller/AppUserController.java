package com.moonshot.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moonshot.restaurant.api.model.APIOrder;
import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.service.AppUserService;
import com.moonshot.restaurant.service.RestaurantService;


@RestController
public class AppUserController {

	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping(method=RequestMethod.GET, value="/appusers")
	public List<AppUser> getAllAppUsers(){
		return appUserService.getAllAppUsers();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/appusers/{id}")
	public AppUser getAppUser(@PathVariable Long id){
		return appUserService.getAppUser(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/appusers")
	public void addAppUser(@RequestBody AppUser appUser){
		appUserService.addAppUser(appUser);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/appusers/{id}")
	public void updateAppUser(@PathVariable Long id, @RequestBody AppUser appUser){
		appUserService.updateAppUser(id, appUser);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/appusers/{id}")
	public void deleteAppUser(@PathVariable Long id){
		appUserService.deleteAppUser(id);		
	}
	
	/*
	 * 	@RequestMapping(method=RequestMethod.GET, value="/orders/users/{userId}/all")
	public List<APIOrder> getAllOrdersForUser(@PathVariable Long userId){
		//return orderService.getAllOrdersByRestaurant(restaurantId);
		return apiOrderService.getAllOrdersForUser(userId);
		
	}
	 */
	
}
