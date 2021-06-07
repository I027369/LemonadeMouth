package com.moonshot.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.moonshot.restaurant.controller.ResturantAddressController;
import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.entity.RestaurantAccountUser;
import com.moonshot.restaurant.entity.RestaurantAddress;
import com.moonshot.restaurant.repository.AppUserRepository;
import com.moonshot.restaurant.repository.RestaurantActUserRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class RestaurantAccountUserService {

	@Autowired
	private RestaurantActUserRepository restActUserRepository;
	@Autowired
	private RestaurantService restaurantService;
	
	public List<RestaurantAccountUser> getAllRestActUsers() {
		// TODO Auto-generated method stub
		List<RestaurantAccountUser> restActUserList = new ArrayList<>();
		restActUserRepository.findAll()
		.forEach(r->restActUserList.add(r));
		return restActUserList;
	}
	
	public List<RestaurantAccountUser> getAllRestActUsersByRest(long id) {
		// TODO Auto-generated method stub
		List<RestaurantAccountUser> restActUserList = new ArrayList<>();
		restActUserRepository.findByRestaurantId(id)
		.forEach(r->restActUserList.add(r));
		return restActUserList;
	}

	public RestaurantAccountUser getRestActUserById(Long id) {
		// TODO Auto-generated method stub
		return restActUserRepository.findOne(id);
	}

	public RestaurantAccountUser addRestActUser(RestaurantAccountUser actUser) {
		// TODO Auto-generated method stub
		Restaurant rest = new Restaurant();
		restaurantService.addRestaurant(rest);
		actUser.setRestaurant(rest);
		restActUserRepository.save(actUser);	
		return actUser;
		
	}
	public RestaurantAccountUser addRestActUser(Long restId, RestaurantAccountUser actUser) {
		// TODO Auto-generated method stub
		Restaurant rest = restaurantService.getRestaurant(restId);
		actUser.setRestaurant(rest);
		restActUserRepository.save(actUser);	
		return actUser;
		
	}

	public RestaurantAccountUser updateRestActUser(Long id, RestaurantAccountUser actUser) {
		// TODO Auto-generated method stub
		RestaurantAccountUser existing = restActUserRepository.findOne(id);
		long tempId = existing.getId();
		//Restaurant rest = existing.getRestaurant();
		//BeanUtils.copyProperties(actUser, existing);
		existing = copyAccountUser(existing, actUser);
		existing.setId(tempId);		
		//actUser.setRestaurant(rest);
		restActUserRepository.save(existing);	
		return existing;
	}

	public void deleteRestActUser(Long id) {
		// TODO Auto-generated method stub
		restActUserRepository.delete(id);
		
	}
	
	public RestaurantAccountUser getRestActUserByEmail(String emailId) {
		// TODO Auto-generated method stub
		return restActUserRepository.findByEmailId(emailId);
	}
	private RestaurantAccountUser copyAccountUser(RestaurantAccountUser existingUser, RestaurantAccountUser newUser){
	
		if(newUser.getEmailId() != null)
			existingUser.setEmailId(newUser.getEmailId());
		if(newUser.getName() != null)
			existingUser.setName(newUser.getName());
		if(newUser.getPassword() != null)
			existingUser.setPassword(newUser.getPassword());
		if(newUser.getRole() != null)
			existingUser.setRole(newUser.getRole());
		if(newUser.getCompany() != null)
			existingUser.setCompany(newUser.getCompany());
		if(newUser.getPhone() != null)
			existingUser.setPhone(newUser.getPhone());
		if(newUser.getRestaurant() != null)
			existingUser.setRestaurant(newUser.getRestaurant());	
		//System.out.println("in the copyAccount User "+newUser.getBlocked());
		/*if(newUser.getBlocked() != null)
			existingUser.setBlocked(newUser.getBlocked());	*/
		return existingUser;
		
	}

}
