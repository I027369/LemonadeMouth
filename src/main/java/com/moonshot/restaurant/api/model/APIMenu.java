package com.moonshot.restaurant.api.model;

import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.moonshot.restaurant.entity.MenuItem;


public class APIMenu {

	private Long id;
	private String name;
	private String status;

	private String availableFrom;

	private String  availableTo;
	
	private Long restaurantId;
	
	//private List<APIMenuItem> menuItem = new ArrayList<>();
	public APIMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIMenu(Long id, String name, String status, String availableFrom, String availableTo,
			Long restaurantId) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.availableFrom = availableFrom;
		this.availableTo = availableTo;
		this.restaurantId = restaurantId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(String availableFrom) {
		this.availableFrom = availableFrom;
	}

	public String getAvailableTo() {
		return availableTo;
	}

	public void setAvailableTo(String availableTo) {
		this.availableTo = availableTo;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

		
}
