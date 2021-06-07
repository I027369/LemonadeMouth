package com.moonshot.restaurant.api.model;

import com.moonshot.restaurant.entity.MenuItem;

public class APIMenuItemOption {
	
	private Long id;
	private String name;
	private String description;
	private float price;
	private String currency;
	private Long menuItemChoiceId;
	public APIMenuItemOption() {
		super();
		// TODO Auto-generated constructor stub
	}
	public APIMenuItemOption(Long id, String name, String description, float price, String currency, Long menuItemChoiceId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.currency = currency;
		this.menuItemChoiceId = menuItemChoiceId;
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
	public Long getMenuItemChoiceId() {
		return menuItemChoiceId;
	}
	public void setMenuItemChoiceId(Long menuItemChoiceId) {
		this.menuItemChoiceId = menuItemChoiceId;
	}
	

}
