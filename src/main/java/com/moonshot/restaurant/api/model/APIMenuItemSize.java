package com.moonshot.restaurant.api.model;


public class APIMenuItemSize {
	
	private Long id;
	private String name;
	private String description;
	private float price;
	private String currency;
	private Long menuItemId;
	public APIMenuItemSize() {
		super();
		// TODO Auto-generated constructor stub
	}
	public APIMenuItemSize(Long id, String name, String description, float price, String currency, Long menuItemId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.currency = currency;
		this.menuItemId = menuItemId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Long getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}
	

}
