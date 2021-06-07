package com.moonshot.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.entity.RestaurantAccountUser;
import com.moonshot.restaurant.service.AppUserService;
import com.moonshot.restaurant.service.RestaurantAccountUserService;
import com.moonshot.restaurant.service.RestaurantService;

//@CrossOrigin(origins = "*")
@RestController
public class RestaurantAccountUserController {

	@Autowired
	private RestaurantAccountUserService appRestActUserService;
	
	@RequestMapping(method=RequestMethod.GET, value="/accountusers")
	public List<RestaurantAccountUser> getAllRestActUsers(){
		return appRestActUserService.getAllRestActUsers();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/restaurant/{restId}/accountusers")
	public List<RestaurantAccountUser> getAllRestActUsers(@PathVariable Long restId){
		return appRestActUserService.getAllRestActUsersByRest(restId);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/accountusers/{id}")
	public RestaurantAccountUser getRestActUserById(@PathVariable Long id){
		return appRestActUserService.getRestActUserById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/accountusers/emails/{email:.+}")
	public RestaurantAccountUser getRestActUser(@PathVariable String email){
		return appRestActUserService.getRestActUserByEmail(email);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/accountusers")
	public RestaurantAccountUser addRestActUser(@RequestBody RestaurantAccountUser restActUser){
		return appRestActUserService.addRestActUser(restActUser);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/restaurant/{restId}/accountusers")
	public RestaurantAccountUser addRestActUser(@PathVariable Long restId, @RequestBody RestaurantAccountUser restActUser){
		return appRestActUserService.addRestActUser(restId, restActUser);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/accountusers/{id}")
	public RestaurantAccountUser updateRestActUser(@PathVariable Long id, @RequestBody RestaurantAccountUser restActUser){
		return appRestActUserService.updateRestActUser(id, restActUser);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/accountusers/{id}")
	public void deleteRestActUser(@PathVariable Long id){
		appRestActUserService.deleteRestActUser(id);		
	}
	
}
