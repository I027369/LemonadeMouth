package com.moonshot.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.OrderPayment;
import com.moonshot.restaurant.entity.RestaurantTable;

public interface OrderPaymentRepository extends CrudRepository<OrderPayment, Long> {

	public List<OrderPayment> findByOrderId(Long id);	
}
