package com.moonshot.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.Order;
import com.moonshot.restaurant.entity.OrderItem;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.entity.RestaurantAddress;
import com.moonshot.restaurant.entity.RestaurantTable;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

	public List<OrderItem> findByOrderId(Long id);
}
