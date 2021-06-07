package com.moonshot.restaurant.api.model;

import java.util.List;

public class APIMenuDetails extends APIMenu{
	List<APIMenuItem> menuItem;

	public APIMenuDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIMenuDetails(Long id, String name, String status, String availableFrom, String availableTo,
			Long restaurantId) {
		super(id, name, status, availableFrom, availableTo, restaurantId);
		// TODO Auto-generated constructor stub
	}

	public APIMenuDetails(List<APIMenuItem> menuItem) {
		super();
		this.menuItem = menuItem;
	}

	public List<APIMenuItem> getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(List<APIMenuItem> menuItem) {
		this.menuItem = menuItem;
	}
	
}
