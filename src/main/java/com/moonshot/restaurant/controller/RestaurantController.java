package com.moonshot.restaurant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.service.RestaurantService;

import io.swagger.annotations.ApiParam;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping("/restaurants")
	public List<Restaurant> getResturants(){
		return restaurantService.getAllRestaurants();
	}
	
	@RequestMapping("/restaurants/{id}")
	public Restaurant getRestaurant(@PathVariable Long id){
		return restaurantService.getRestaurant(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/restaurants")
	public Restaurant addRestaurant(@RequestBody Restaurant restaurant) throws WriterException {
		return restaurantService.addRestaurant(restaurant);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/restaurants/{id}")
	public Restaurant updateRestaurant(
			@PathVariable Long id, 
			@RequestBody Restaurant restaurant,
			@ApiParam(value = "Example text \"?status=Pulished\"", required = false) @RequestParam Map<String, String> queryParameters	
			) throws WriterException {
		String localStatus = queryParameters.get("status");
		if(localStatus != null){				
			return restaurantService.updateRestaurantStatus(id,localStatus);
		}
		else
			return restaurantService.updateRestaurant(id, restaurant);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/restaurants/{id}")
	public void deleteRestaurant(@PathVariable Long id) throws WriterException {
		restaurantService.deleteRestaurant(id);		
	}
	
}
