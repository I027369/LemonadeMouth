package com.moonshot.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.Order;
import com.moonshot.restaurant.entity.RestaurantAddress;


public interface OrderRepository extends CrudRepository<Order, Long> {
	
	public List<Order> findByRestaurantId(Long id);
	public List<Order> findByRestaurantTableId(Long id);
	public List<Order> findByAppUserId(Long id);
	public List<Order> findByStatus(String status);
}
