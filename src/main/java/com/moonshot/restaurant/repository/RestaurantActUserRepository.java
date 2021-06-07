package com.moonshot.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.RestaurantAccountUser;
import com.moonshot.restaurant.entity.RestaurantAddress;

public interface RestaurantActUserRepository extends CrudRepository<RestaurantAccountUser, Long> {

	public List<RestaurantAccountUser> findByRestaurantId(Long id);
	public RestaurantAccountUser findByEmailId(String emailId);
}
