package com.moonshot.restaurant.api.model;

public class APIMenuItemCategory {
	
	private Long id;
	private String name;
	private int sortOrder;
	//private boolean active;
	private Long restaurantId;
	public APIMenuItemCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public APIMenuItemCategory(Long id, String name, String description, int sortOrder, Long restaurantId) {
		super();
		this.id = id;
		this.name = name;
		this.sortOrder = sortOrder;
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
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	

}
