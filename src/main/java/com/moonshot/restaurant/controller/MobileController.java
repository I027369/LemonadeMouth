package com.moonshot.restaurant.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.moonshot.restaurant.api.model.APIMenu;
import com.moonshot.restaurant.api.model.APIMenuDetails;
import com.moonshot.restaurant.api.model.APIMenuItem;
import com.moonshot.restaurant.api.model.APIMenuWithItemAndCategory;
import com.moonshot.restaurant.api.model.APIOrder;
import com.moonshot.restaurant.api.model.PaymentRequest;
import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.RestaurantTable;
import com.moonshot.restaurant.service.MenuItemService;
import com.moonshot.restaurant.service.MenuService;
import com.moonshot.restaurant.service.OrderService;
import com.moonshot.restaurant.service.RestaurantTableService;
import com.stripe.model.Charge;

import io.swagger.annotations.ApiParam;


@RestController
public class MobileController {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method=RequestMethod.GET, value="/mobile/restaurants/{restaurantId}/menus")
	public ResponseEntity<?> getMenus( @PathVariable Long restaurantId) throws WriterException, ParseException{	
		List<APIMenuWithItemAndCategory> apiMenu = menuService.getAvailableMenu(restaurantId);
		if (apiMenu.isEmpty()){
			return ResponseEntity.accepted().body("No menus/menu items are available at this time");
		}else
			return ResponseEntity.accepted().body(apiMenu);		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/mobile/orders/users/{userId}")
	public List<APIOrder> getActiveOrderForUser(
			@PathVariable Long userId,
			@ApiParam(value = "Example text \"?status=New\"", required = false) @RequestParam Map<String, String> queryParameters){
		String localStatus = queryParameters.get("status");
		if(localStatus != null)			
			return orderService.getActiveOrderForUser(userId, localStatus);	
		else			
			return orderService.getAllOrdersForUser(userId);			
			
	}

		
}
