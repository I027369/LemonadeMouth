package com.moonshot.restaurant.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.api.model.APIOrderItem;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.OrderItem;
import com.moonshot.restaurant.entity.RestaurantTable;
import com.moonshot.restaurant.service.MenuItemService;
import com.moonshot.restaurant.service.OrderItemService;
import com.moonshot.restaurant.service.RestaurantTableService;



@RestController
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;
	

	@RequestMapping(method=RequestMethod.GET, value="/orders/{orderId}/orderitems")
	public List<APIOrderItem> getAllOrderItems(@PathVariable Long orderId){
		return orderItemService.getAllOrderItems(orderId);
	}
	

	@RequestMapping(method=RequestMethod.GET, value="/orderitems/{orderitemId}")
	public APIOrderItem getOrderItem(@PathVariable Long orderitemId){
		return orderItemService.getOrderItem(orderitemId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/orders/{orderId}/orderitems")
	public APIOrderItem addOrderItem(@RequestBody APIOrderItem orderitem, @PathVariable Long orderId)throws WriterException, IOException{	
		return orderItemService.addOrderItem(orderId, orderitem);			
	}	
	
		
	@RequestMapping(method=RequestMethod.PUT, value="/orderitems/{orderitemId}")
	public APIOrderItem updateOrderItem(@RequestBody APIOrderItem orderitem, @PathVariable Long orderitemId)throws WriterException, IOException{
		return orderItemService.updateOrderItem(orderitemId, orderitem);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/orderitems/{orderitemId}")
	public void deleteOrderItem(@PathVariable Long orderitemId){
		orderItemService.deleteOrderItem(orderitemId);
	}
	
}
