package com.moonshot.restaurant.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.entity.RestaurantTable;
import com.moonshot.restaurant.repository.MenuItemRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;
import com.moonshot.restaurant.repository.RestaurantTableRepository;

@Service
public class RestaurantTableService {
	@Autowired
	private RestaurantTableRepository restaurantTableRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;

	public List<RestaurantTable> getAllRestaurantTables(Long restaurantId) {
		// TODO Auto-generated method stub
		return restaurantTableRepository.findByRestaurantId(restaurantId);
	}


	public RestaurantTable getRestaurantTable(Long restaurantTableid) {
		// TODO Auto-generated method stub
		RestaurantTable restTable = restaurantTableRepository.findOne(restaurantTableid);
		
		return restTable;
	}
	

	public RestaurantTable addRestaurantTable(Long restaurantId, RestaurantTable restaurantTable) throws WriterException, IOException{
		// TODO Auto-generated method stub
		Restaurant rest = restaurantRepository.findOne(restaurantId);
		restaurantTable.setRestaurant(rest);
		restaurantTableRepository.save(restaurantTable);	
		return restaurantTable;
				
	}

	public RestaurantTable updateRestaurantTable(Long restaurantTableId, RestaurantTable restaurantTable) throws WriterException, IOException {
		// TODO Auto-generated method stub
		RestaurantTable existingItem = restaurantTableRepository.findOne(restaurantTableId);
		Long tempId = existingItem.getId();
		Restaurant rest = existingItem.getRestaurant();
		BeanUtils.copyProperties(restaurantTable, existingItem);
		existingItem.setId(tempId);
		existingItem.setRestaurant(rest);
		restaurantTableRepository.save(existingItem);
		return existingItem;
	}

	public void deleteRestaurantTable(Long restaurantTableId) {
		// TODO Auto-generated method stub
		restaurantTableRepository.delete(restaurantTableId);
		
	}

}
