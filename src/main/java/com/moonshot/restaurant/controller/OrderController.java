package com.moonshot.restaurant.controller;

import java.io.IOException;
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
import com.moonshot.restaurant.api.model.APIOrder;
import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.Order;
import com.moonshot.restaurant.entity.OrderItem;
import com.moonshot.restaurant.entity.RestaurantTable;
import com.moonshot.restaurant.service.MenuItemService;
import com.moonshot.restaurant.service.OrderItemService;
import com.moonshot.restaurant.service.OrderService;
import com.moonshot.restaurant.service.RestaurantTableService;

import io.swagger.annotations.ApiParam;



@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method=RequestMethod.GET, value="/restaurants/{restaurantId}/orders/all")
	public List<APIOrder> getAllOrderByRestaurant(@PathVariable Long restaurantId){
		//return orderService.getAllOrdersByRestaurant(restaurantId);
		return orderService.getAllOrderByRestaurant(restaurantId);		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/restaurants/{restaurantId}/orders")
	public List<APIOrder> getActiveOrdersByRestaurant(
			@PathVariable Long restaurantId,
			@ApiParam(value = "Example text \"?status=New\"", required = false) @RequestParam Map<String, String> queryParameters){
		String localStatus = queryParameters.get("status");
		if(localStatus != null)			
			return orderService.getActiveOrdersByRestaurant(restaurantId, localStatus);
		else			
			return orderService.getAllOrderByRestaurant(restaurantId);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/orders/tables/{tableId}")
	public List<APIOrder> getactiveOrderForTable(
			@PathVariable Long tableId,
			@ApiParam(value = "Example text \"?status=New\"", required = false) @RequestParam Map<String, String> queryParameters){
		String localStatus = queryParameters.get("status");
		if(localStatus != null)			
			return orderService.getActiveOrderForTable(tableId, localStatus);
		else			
			return orderService.getAllOrderForTable(tableId);
				
	}
		

	@RequestMapping(method=RequestMethod.GET, value="/orders/{orderId}")
	public APIOrder getOrder(@PathVariable Long orderId) throws WriterException{
		//return orderService.getAllOrdersByRestaurant(restaurantId);
		return orderService.getOrder(orderId);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/orders")
	public APIOrder createOrder(@RequestBody APIOrder order)throws WriterException, IOException{	
		return orderService.createOrder(order);			
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/orders/{orderId}")
	public APIOrder updateOrder(
			@RequestBody (required=false) APIOrder order, 
			@PathVariable Long orderId,
			@ApiParam(value = "Example text \"?status=Paid\"", required = false) @RequestParam Map<String, String> queryParameters
			)throws WriterException, IOException{
		String localStatus = queryParameters.get("status");
		if(localStatus != null){				
			return orderService.updateOrderStatus(orderId,localStatus);
		}
		else
		return orderService.updateOrder(order, orderId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/orders/{orderId}")
	public void deleteOrder(@PathVariable Long orderId)throws WriterException{
		orderService.deleteOrder(orderId);
	}

	
}
