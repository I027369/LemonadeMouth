package com.moonshot.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.controller.ResturantAddressController;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.entity.RestaurantAddress;
import com.moonshot.restaurant.repository.RestaurantRepository;
import com.moonshot.restaurant.util.BeanUtilsIgnoreNull;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public List<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		List<Restaurant> restaurantList = new ArrayList<>();
		restaurantRepository.findAll()
		.forEach(r->restaurantList.add(r));
		return restaurantList;
	}

	public Restaurant getRestaurant(Long id) {
		// TODO Auto-generated method stub
		return restaurantRepository.findOne(id);
	}

	public Restaurant addRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		restaurant.setState("Draft");
		restaurantRepository.save(restaurant);	
		return restaurant;
	}

	public Restaurant updateRestaurant(Long id, Restaurant restaurant) throws WriterException {
		// TODO Auto-generated method stub
		Restaurant existingRestaurant = restaurantRepository.findOne(id);
		if (existingRestaurant == null ){
			throw new WriterException("No restaurant found");
		}
		long tempId = existingRestaurant.getId();
		BeanUtilsIgnoreNull.myCopyProperties(restaurant, existingRestaurant);
		//BeanUtils.copyProperties(restaurant, existingRestaurant);
		//existingRestaurant = copyRestData(restaurant, existingRestaurant);
		existingRestaurant.setId(tempId);
		restaurantRepository.save(existingRestaurant);	
		return existingRestaurant;
	}
	
	private Restaurant copyRestData(Restaurant restaurant, Restaurant existing){
		/*if(restaurant.getAddress() != null)
			existing.setAddress(restaurant.getAddress());
		if(restaurant.getBackgroundColor() != null)
			existing.setBackgroundColor(restaurant.getBackgroundColor());
		if(restaurant.getBackgroundImage() != null)
			existing.setBackgroundImage(restaurant.getBackgroundImage());
		if(restaurant.getCity() != null)
			existing.setCity(restaurant.getCity());
		if(restaurant.getCountry() != null)
			existing.setCountry(restaurant.getCountry());
		if(restaurant.getCurrency() != null)
			existing.setCurrency(restaurant.getCurrency());
		if(restaurant.getEmail() != null)
			existing.setEmail(restaurant.getEmail());
		if(restaurant.getLogo() != null)
			existing.setLogo(restaurant.getLogo());
		*/
		return existing;
	}

	public void deleteRestaurant(Long id) throws WriterException {
		// TODO Auto-generated method stub
		Restaurant existingRestaurant = restaurantRepository.findOne(id);
		if (existingRestaurant == null ){
			throw new WriterException("No restaurant found");
		}
		restaurantRepository.delete(id);
		
	}

	public Restaurant updateRestaurantStatus(Long id, String localStatus) throws WriterException{
		// TODO Auto-generated method stub
		Restaurant existingRestaurant = restaurantRepository.findOne(id);
		if (existingRestaurant == null ){
			throw new WriterException("No restaurant found");
		}
		existingRestaurant.setStatus(localStatus);
		restaurantRepository.save(existingRestaurant);	
		return existingRestaurant;
	}

}
